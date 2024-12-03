package com.icarus.recycle_app.ui.search.image.trash_request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewOutlineProvider
import com.bumptech.glide.Glide
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityTrashRequestBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.home.HomeFragment
import com.icarus.recycle_app.utils.ServerConnectHelper

class TrashRequestActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTrashRequestBinding
    private val serverConnectHelper = ServerConnectHelper()

    private val binding get() = _binding
    private var _type = 0


    //Intent Type 0 - 일반검색 1- 카메라/갤러리 검색


    private lateinit var testTrash : Trash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityTrashRequestBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.ivTrash.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.ivTrash.clipToOutline = true



        _type = intent.getIntExtra("type",0)

        initListener()
        when(_type){

            //일반 검색
            0 -> {
                val trashObject = intent.getParcelableExtra<Trash>("trash")
                if (trashObject != null) {
                    // Trash 객체가 전달되었으므로 이를 사용할 수 있습니다.
                    initRequestData(trashObject)
                }
            }

            //카메라/갤러리 검색
            1-> {
                // 서버 요청 리스너 등록
                serverConnectHelper.request = object : ServerConnectHelper.RequestServer {
                    override fun onSuccess(testPost: TestPost) {
                        // 데이터 가져오기 성공
                        println("Post title: ${testPost.title}")

                        // 임시 데이터
                        initRequestData(testTrash)

                        // 데이터 입력이 끝나면
                        setContentView(binding.root)
                    }


                    override fun onFailure() {
                        // 서버 요청에 실패 했을 때
                        setContentView(binding.root)
                    }

                }

                // 서버 요청 실행
                serverConnectHelper.getPost()
            }
        }




    }


    private fun initRequestData(trash: Trash) {
        binding.tvToolBarTitle.text = trash.name

        binding.tvTrashTitle.text = "${trash.name} 정보"

        Glide.with(applicationContext)
            .load(trash.image) // item.image는 이미지 파일의 경로 문자열
            .into(binding.ivTrash)

        binding.tvTrashType.text = trash.type + "\n\n" + trash.recycleAble

        binding.tvTrashThrowInfoTitle.text = "${trash.name}의 버리는 방법"

        binding.tvTrashTipInfoTitle.text = "${trash.name}의 알아두면 좋은 점"

        trash.method = trash.method.replace("|", "\n\n\n")
        binding.tvTrashThrowInfo.text = "${trash.method}"

        trash.etc = trash.etc.replace("|", "\n\n\n")
        binding.tvTrashTipInfo.text = "${trash.etc}"

        binding.ibFavorite.setOnClickListener {
            favoriteUpdate(trash.id)
        }

        favoriteInit(trash.id)

    }

    private fun favoriteInit(id: Int){
        //favorite 확인
        val map = AppManager.getFavorites()
        if(map.containsKey(id)){
            if(map[id]==true){
                binding.ibFavorite.setImageResource(R.drawable.ic_favorite)
            }else{
                binding.ibFavorite.setImageResource(R.drawable.ic_blank_favorite)
            }
        }else{
            binding.ibFavorite.setImageResource(R.drawable.ic_blank_favorite)
            AppManager.setFavorites(id,false)
        }
    }
    private fun favoriteUpdate(id: Int){//버그 있음
        //favorite 확인
        val map = AppManager.getFavorites()
        if(map[id]==true){
            binding.ibFavorite.setImageResource(R.drawable.ic_blank_favorite)
            AppManager.setFavorites(id,false)
        }else if(map[id]==false){
            binding.ibFavorite.setImageResource(R.drawable.ic_favorite)
            AppManager.setFavorites(id,true)
        }
    }



    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}