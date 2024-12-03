package com.icarus.recycle_app.ui.info.content

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityInfoContentBinding
import com.icarus.recycle_app.databinding.ActivityMainBinding
import com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol.RecyclingSymbolFragment
import com.icarus.recycle_app.ui.info.placeholder.PlaceholderContent

/**
 * 쓰레기 안내를 표시할 액티비티
 */
class InfoContentActivity : AppCompatActivity() {


    private var _binding: ActivityInfoContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInfoContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 액티비티가 시작될 때 intent를 불러옴
        val intent = intent
        val id = intent.getStringExtra("id")

        // intent에서 fragment를 불러오고
        val item = PlaceholderContent.ITEM_MAP[id]

        val fragment = item!!.fragment
        binding.toolbar.title = item.title

        // 프레그먼트 실행
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commitNow()

    }
}