package com.icarus.recycle_app.ui.search.image.trash_request

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.icarus.recycle_app.adapters.CategoryResultAdapter
import com.icarus.recycle_app.databinding.ActivityCategoryResultBinding
import com.icarus.recycle_app.databinding.ActivityImageResultBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.SearchImageViewModel
import com.icarus.recycle_app.utils.ServerConnectHelper

class ImageResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageResultBinding
    private val serverConnectHelper = ServerConnectHelper()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

        val bundle = intent.extras
        val myArrayList = bundle?.getParcelableArrayList<Trash>("myKey")

        binding.tvToolBarTitle.text = "유사 품목 검색 결과"

        Log.d("asd", myArrayList.toString())

        if (!myArrayList.isNullOrEmpty()){
            val adapter = CategoryResultAdapter(myArrayList.toList(), applicationContext)
            binding.recyclerView.adapter = adapter

            // RecyclerView에 GridLayoutManager를 설정하여 한 줄에 2개의 아이템이 표시되도록 합니다.
            binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        } else {
            binding.tvNoneTrash.visibility = View.VISIBLE
        }


        Log.d("asd", myArrayList.toString())

    }

    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}