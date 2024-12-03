package com.icarus.recycle_app.ui.search.image

import android.Manifest
import android.animation.ObjectAnimator
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSearchImageBinding
import com.icarus.recycle_app.dto.Image
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.dto.TrashImage
import com.icarus.recycle_app.ui.search.SearchViewModel
import com.icarus.recycle_app.ui.search.image.trash_request.ImageResultActivity
import com.icarus.recycle_app.utils.CameraHelper
import com.icarus.recycle_app.utils.LoadingUtil
import com.icarus.recycle_app.utils.PathUtil
import com.icarus.recycle_app.utils.ServerConnectHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.lang.Exception

class SearchImageFragment : Fragment() {


    private var _binding: FragmentSearchImageBinding? = null

    private val binding get() = _binding!!


    private val REQUEST_GALLERY_IMAGE = 2 // 갤러리 요청 코드 추가

    private lateinit var viewModel: SearchImageViewModel

    private lateinit var loadingUtil: LoadingUtil

    private val serverConnectHelper = ServerConnectHelper()


    companion object {
        private const val ARG_TYPE = "click_btn"
        fun newInstance(type: Int?): SearchImageFragment {
            val fragment = SearchImageFragment()
            val args = Bundle()

            // 값이 null이 아닐 경우에만 번들에 넣는다.
            type?.let {
                args.putInt(ARG_TYPE, it)
            }



            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SearchImageViewModel::class.java]

        viewModel.cameraHelper = CameraHelper(requireActivity())

        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),
            viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE)



//        viewModel.navigateToNextActivity.observe(this) { shouldNavigate ->
//            if (shouldNavigate) {
//
//
//                // Reset the navigation trigger
//                viewModel.navigateToNextActivity.value = false
//            }
//        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchImageBinding.inflate(inflater, container, false)

        binding.ivCameraResult.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.ivCameraResult.clipToOutline = true

        loadingUtil = LoadingUtil(requireContext())
        loadingUtil.initUtil("이미지 변환 중...", "어떻게 동작하나요?",
            mutableListOf(
                "1. 촬영된 이미지를 서버를 통해 인공지능에게 전달합니다.",
                "2. 미리 학습된 16가지 클래스를 기준으로 이미지를 감지하고 판별합니다.",
                "3. 반환된 클래스와 비슷한 성질의 쓰레기를 데이터베이스에서 반환합니다.",
                "4. 인공지능의 실시간 이미지 분석이 사용되므로 시간이 걸릴 수 있습니다."),
            mutableListOf(binding.fabBack,
                    binding.tvInfo,
                    binding.btnSend),
            R.drawable.ic_arrow_down1_black,
            R.drawable.ic_arrow_up1_balck)



        val downArrow = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_down1_black)
        val upArrow = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_up1_balck)

        initListener()

        val slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
        val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)

        viewModel.isClickedTextInfo.observe(requireActivity()) {
            if (viewModel.isClickedTextInfo.value == true) {
                binding.tvInfoChild.startAnimation(slideDown)
                binding.tvInfoChild.visibility = View.VISIBLE
                binding.tvInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, upArrow, null)
                ObjectAnimator.ofInt(binding.nestedScrollView, "scrollY", binding.tvInfoChild.bottom).apply {
                    duration = 1000
                    start()
                }


            } else {
                binding.tvInfoChild.startAnimation(slideUp)
                binding.tvInfoChild.visibility = View.INVISIBLE
                binding.tvInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, downArrow, null)
            }
        }

        viewModel.imageResultUri.observe(requireActivity()) {
            if (viewModel.imageResultUri.value != null) {
                Glide.with(requireActivity())
                    .load(viewModel.imageResultUri.value)
                    .into(binding.ivCameraResult)
            }
        }

        viewModel.isCameraOpened.observe(requireActivity()) {
            if (viewModel.isCameraOpened.value == true) {
                loadingUtil.dismiss()
                loadingUtil.changeVisibility(View.VISIBLE)

            } else {
                loadingUtil.show()
                loadingUtil.changeVisibility(View.INVISIBLE)
            }
        }

        if (viewModel.isCameraOpened.value == false) {

            when (arguments?.getInt(ARG_TYPE)) {
                0 -> takePhotoFromCamera()
                1 -> openGallery()
                else -> {
                    // 다른 동작
                }
            }
        }

        return binding.root
    }

    /**
     * 리스너 등록
     */
    private fun initListener() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnSend.setOnClickListener {
            loadingUtil.show("인공지능 이미지 식별 중...")

            // 서버 요청 리스너 등록
            serverConnectHelper.requestImageUpload = object : ServerConnectHelper.RequestImageUpload {
                override fun onSuccess(trashes: List<Trash>) {

                    Log.d("asd", "전송 성공")

                    for (item in trashes) {
                        Log.d("asd", item.toString())
                    }

                    loadingUtil.dismiss()

                    val intent = Intent(activity,ImageResultActivity::class.java)
                    val bundle = Bundle()

                    bundle.putParcelableArrayList("myKey", ArrayList(trashes))
                    intent.putExtras(bundle)

                    startActivity(intent)
                }

                override fun onFailure() {
                    loadingUtil.dismiss()
                    Log.d("asd", "전송 실패")
                    Toast.makeText(requireContext(), "서버와 응답이 없습니다.", Toast.LENGTH_SHORT).show()
                }

            }

            // 서버 요청 실행
            viewModel.imageResultUri.value?.let {
                val byteArray = PathUtil.uriToByteArray(requireContext(), it)
                serverConnectHelper.uploadImage(byteArray!!)
            }
        }

        binding.tvInfo.setOnClickListener {
            viewModel.toggleIsClickedTextInfo()
        }
    }




    private fun takePhotoFromCamera() {
        val intent = viewModel.cameraHelper.dispatchTakePictureIntent()
        intent?.let {
            startActivityForResult(it, viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE)
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_IMAGE)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        lifecycleScope.launch {
            if (requestCode == viewModel.cameraHelper.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                viewModel.imageResultUri.value = viewModel.cameraHelper.getPhotoUri()
            } else if (requestCode == REQUEST_GALLERY_IMAGE && resultCode == RESULT_OK) {
                viewModel.imageResultUri.value = data?.data
            }

//            // Uri를 Bitmap으로 변환
//            val imageBitmap = async(Dispatchers.Default) { createBitmap(viewModel.imageResultUri.value) }
//            //async { convertBitmapToByteArray(imageBitmap.await()) }.await()
//            async(Dispatchers.Default) { convertBitmapToByteArray(imageBitmap.await()) }.await()

            viewModel.isCameraOpened.value = true
        }

    }

//    private fun createBitmap(uri: Uri?): Bitmap {
//        return MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
//    }
//
//    private fun convertBitmapToByteArray(imageBitmap: Bitmap) {
//        // Bitmap을 ByteArray로 변환
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//        viewModel.imageByteArray = byteArrayOutputStream.toByteArray()
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}