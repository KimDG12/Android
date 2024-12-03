package com.icarus.recycle_app.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSearchBarcodeBinding
import com.icarus.recycle_app.ui.search.barcode.SearchBarcodeFragment
import com.icarus.recycle_app.ui.search.image.SearchImageFragment

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        val intent = intent
        val typeClickValue = intent.getIntExtra("click_btn", -1)

        when (typeClickValue) {
            0, 1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchImageFragment.newInstance(typeClickValue))
                    .commitNow()
            }
            2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchBarcodeFragment.newInstance())
                    .commitNow()
            }
            else -> {
                // 오류 처리 또는 기본 로직
            }
        }
    }
}