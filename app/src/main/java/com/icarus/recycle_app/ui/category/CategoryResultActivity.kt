package com.icarus.recycle_app.ui.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.icarus.recycle_app.adapters.CategoryResultAdapter
import com.icarus.recycle_app.databinding.ActivityCategoryResultBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.utils.LoadingUtil
import com.icarus.recycle_app.utils.ServerConnectHelper

class CategoryResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryResultBinding
    private val serverConnectHelper = ServerConnectHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

        // 더 많은 아이템을 추가할 수 있습니다.

        val loadingUtil = LoadingUtil(this)
        loadingUtil.show()

        try {
            val category = intent.getStringExtra("category") ?: throw IllegalArgumentException("Category is null")
            serverConnectHelper.requestCategoryTrashes = object : ServerConnectHelper.RequestCategoryTrashes{
                override fun onSuccess(trashes: List<Trash>) {
                    loadingUtil.dismiss()

                    val adapter = CategoryResultAdapter(trashes, applicationContext)
                    binding.recyclerView.adapter = adapter

                    // RecyclerView에 GridLayoutManager를 설정하여 한 줄에 2개의 아이템이 표시되도록 합니다.
                    binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

                    Log.d("numberaa",trashes.toString())
                }

                override fun onFailure() {

                    loadingUtil.dismiss()
                    binding.tvNoneTrash.visibility = View.VISIBLE
                }

            }
            serverConnectHelper.getCategoryTrashes(category,this)
            binding.tvToolBarTitle.text = category

        }catch (e: IllegalArgumentException) {
            Log.e("error", "Category is missing", e)
            // 적절한 에러 처리
        } catch (e: Exception) {
            Log.e("error", "An unexpected error occurred", e)
            // 적절한 에러 처리
        }






    }

    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}