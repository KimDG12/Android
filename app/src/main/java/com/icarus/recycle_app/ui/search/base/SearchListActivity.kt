package com.icarus.recycle_app.ui.search.base
import SearchListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.icarus.recycle_app.R

import com.icarus.recycle_app.databinding.ActivitySearchListBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.trash_request.TestPost
import com.icarus.recycle_app.ui.search.image.trash_request.TrashRequestActivity
import com.icarus.recycle_app.utils.ServerConnectHelper

class SearchListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchListBinding
    private val serverConnectHelper = ServerConnectHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()

        serverConnectHelper.requestTrashes = object : ServerConnectHelper.RequestTrashes {
            override fun onSuccess(trashes: List<Trash>) {
                val adapter = SearchListAdapter(applicationContext,trashes)
                binding.autoCompleteTextView.setAdapter(adapter)
                Log.d("numberss2",trashes.toString())
                //자동완성 선택시 이름만 입력되게

                binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
                    val selectedTrash = adapter.getItem(position) as Trash
                    binding.autoCompleteTextView.setText(selectedTrash.name, false)
                    binding.autoCompleteTextView.setSelection(selectedTrash.name.length) // 커서 위치 설정

                    // 여기에서 검색 결과 화면으로 이동하는 코드를 추가합니다.
                    // intent에 쿼리전달
                    val intent = Intent(applicationContext, TrashRequestActivity::class.java)
                    intent.putExtra("trash", selectedTrash)
                    intent.putExtra("type",0)
                    startActivity(intent)
                }
            }

            override fun onFailure() {
                Log.d("numberss2","씰패")
            }

        }
        serverConnectHelper.getTrashes()





    }
    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }


}