package com.icarus.recycle_app.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.adapters.HomeAdapter
import com.icarus.recycle_app.databinding.ActivityBookmarkListBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.utils.ServerConnectHelper
import java.lang.Exception

class BookmarkListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkListBinding
    private val serverConnectHelper = ServerConnectHelper()
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeAdapter = HomeAdapter(this)
        val layoutManager = GridLayoutManager(this, 4)
        binding.gridView.layoutManager = layoutManager



    }

    override fun onResume() {
        super.onResume()
        updateBookmarkList()
    }

    private fun updateBookmarkList(){
        // 북마크 목록 데이터를 업데이트합니다.
        val filteredKeys = AppManager.getFavorites().filter { it.value }.keys.joinToString(" ")


        try {
            serverConnectHelper.requestMultiTrashes = object : ServerConnectHelper.RequestTrashes{
                override fun onSuccess(trashes: List<Trash>) {

                    for (item in trashes) {

                        Log.d("test", "HomeFragment" + item.toString())
                    }

                    binding.gridView.adapter = homeAdapter
                    homeAdapter.updateData(trashes)

                    //setGridViewHeightBasedOnChildren(binding.gridView, 4, trashes)
                }

                override fun onFailure() {
                    Log.d("numberss2","씰패")
                }

            }
            serverConnectHelper.getMultiTrashes(filteredKeys)
        }catch (_: Exception){
        }




    }
}
