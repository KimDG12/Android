package com.icarus.recycle_app.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.dto.EnvironmentTip
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.dto.RegionTrashPlaceInfo
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.trash_request.TestPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import java.io.File
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

/**
 * 쓰레기 사진을 전송하여 쓰레기 정보를 반환 받는 클래스
 */
class ServerConnectHelper {

    private val apiService: ApiService
    var request: RequestServer? = null
    var requestRegionInfo: RequestRegionInfo? = null
    var requestImageUpload: RequestImageUpload? = null
    var requestRegionTrashPlaceInfo: RequestRegionTrashPlaceInfo? = null
    var requestTrashes: RequestTrashes? = null
    var requestMultiTrashes: RequestTrashes? = null
    var requestCategoryTrashes: RequestCategoryTrashes? = null

    var requestEnvironmentTip: RequestEnvironment? = null

    var requestTrashesRandom: RequestTrashes? = null



    init {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("http://220.68.27.150:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)  // 여기에 추가
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    /**
     * 서버로부터 응답된 결과에 따라 인터페이스 실행
     */
    fun getPost() {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getPost()
                val response = call.execute()

                if (response.isSuccessful) {

                    withContext(Dispatchers.Main) {


                        request!!.onSuccess(response.body()!!)// Retrofit API 호출
                        // 성공적으로 데이터를 가져왔을 때의 처리

                    }
                } else {
                    withContext(Dispatchers.Main) {
                        request!!.onFailure()
                    }
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main) {
                    request!!.onFailure()
                }
            }

        }
    }

    fun uploadImage(byteArray: ByteArray) {
        val uid = AppManager.getUid()

        // ByteArray to RequestBody
        val requestFile = byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())

        // Using RequestBody to create MultipartBody.Part
        val imagePart = MultipartBody.Part.createFormData("image", "myImage.jpeg", requestFile)
        val uidRequestBody = uid?.toRequestBody("text/plain".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = apiService.uploadImageWithUid(imagePart, uidRequestBody)
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        requestImageUpload?.onSuccess(response.body()!!)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        requestImageUpload?.onFailure()
                    }
                }
            } catch (e: Exception) {
                Log.d("testx", e.toString())
            }
        }
    }





    fun getTrashPlace(roadAdd: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getTrashPlace(roadAdd)
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {

                    requestRegionInfo!!.onSuccess(response.body()!!)

                    }
                } else {
                    withContext(Dispatchers.Main) {
                        requestRegionInfo!!.onFailure()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    requestRegionInfo?.onFailure()
                }
            }

        }
    }

    fun getRegionTrashPlaceInfo(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getRegionTrashPlace(id)
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        requestRegionTrashPlaceInfo!!.onSuccess(response.body()!!)

                    }
                } else {
                    withContext(Dispatchers.Main) {
                        requestRegionTrashPlaceInfo!!.onFailure()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    requestRegionTrashPlaceInfo?.onFailure()
                }
            }

        }
    }

    fun getTrashes(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getTrashes()
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {

                        requestTrashes?.onSuccess(response.body()!!)

                    }
                }else {
                    withContext(Dispatchers.Main) {
                        requestTrashes?.onFailure()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    requestTrashes?.onFailure()
                }
            }

        }
    }

    fun getMultiTrashes(idList: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = apiService.getMultiTrashes(idList)
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        // 성공적으로 데이터를 가져왔을 때의 처리
                        requestMultiTrashes?.onSuccess(response.body()!!)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        requestMultiTrashes?.onFailure()
                    }
                }
            }catch (e: Exception) {
                // 그 외 예외 발생 시 처리
                withContext(Dispatchers.Main) {
                    requestMultiTrashes?.onFailure()
                }
            }
        }
    }


    fun getCategoryTrashes(name: String,context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = apiService.getCategoryTrashes(name)
                val response = call.execute()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        requestCategoryTrashes?.onSuccess(response.body()!!)
                    } else {
                        requestCategoryTrashes?.onFailure()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    requestCategoryTrashes?.onFailure()
                }
            }
        }
    }


    fun getGovermentBlog(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getGovermentBlog()
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        requestEnvironmentTip?.onSuccess(response.body()!!)
                    }
                }else {
                    withContext(Dispatchers.Main) {
                        requestEnvironmentTip?.onFailure()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    requestEnvironmentTip?.onFailure()
                }
            }

        }
    }



    fun getEnvironmentTip(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getEnvironmentTip()
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        requestEnvironmentTip?.onSuccess(response.body()!!)
                    }
                }else {
                    withContext(Dispatchers.Main) {
                        requestEnvironmentTip?.onFailure()
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    requestEnvironmentTip?.onFailure()
                }
            }

        }
    }


    fun getRandomTrashes(count: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val call = apiService.getRandomTrashes(count)
                val response = call.execute()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val items = response.body()!!
                        Log.d("testx", items.size.toString())
                        requestTrashesRandom?.onSuccess(items)
                    }
                }else {
                    withContext(Dispatchers.Main) {
                        requestTrashesRandom?.onFailure()
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    Log.d("testx", e.toString())

                }
            }

        }
    }


    /**
     * retrofit api 인터페이스
     */
    interface ApiService {
        @GET("posts/1")
        fun getPost(): Call<TestPost>

        @Multipart
        @POST("upload")
        fun uploadImageWithUid(
            @Part image: MultipartBody.Part,
            @Part("uid") uid: RequestBody?
        ): Call<List<Trash>>

        @GET("dbwt")
        fun getTrashPlace(@Query("roadAdd") roadAdd: String): Call<List<RegionInfo>>


        @GET("dbhw")
        fun getRegionTrashPlace(@Query("roadAdd") id: Int): Call<RegionTrashPlaceInfo>

        @GET("trashes")
        fun getTrashes(): Call<List<Trash>>

        @GET("multiTrashes")
        fun getMultiTrashes(@Query("idList") idList: String): Call<List<Trash>>

        @GET("category")
        fun getCategoryTrashes(@Query("name") name: String): Call<List<Trash>>


        @GET("news_send")
        fun getEnvironmentTip(): Call<List<EnvironmentTip>>

        @GET("blog_send")
        fun getGovermentBlog(): Call<List<EnvironmentTip>>

        @GET("random_trash_send")
        fun getRandomTrashes(@Query("random_id")count: Int): Call<List<Trash>>
    }

    /**
     * 서버 응답 인터페이스
     */
    interface RequestServer {
        fun onSuccess(testPost: TestPost)
        fun onFailure()
    }

    interface RequestImageUpload {
        fun onSuccess(trashes: List<Trash>)
        fun onFailure()

    }

    interface RequestTrashes {
        fun onSuccess(trashes: List<Trash>)
        fun onFailure()

    }


    interface RequestRegionInfo {
        fun onSuccess(regionInfoList: List<RegionInfo>)
        fun onFailure()
    }

    interface RequestRegionTrashPlaceInfo {
        fun onSuccess(regionTrashPlaceInfo: RegionTrashPlaceInfo)
        fun onFailure()
    }

    interface RequestCategoryTrashes {
        fun onSuccess(trashes: List<Trash>)
        fun onFailure()
    }


    interface RequestEnvironment {
        fun onSuccess(environmentTipList: List<EnvironmentTip>)

        fun onFailure()
    }


}
