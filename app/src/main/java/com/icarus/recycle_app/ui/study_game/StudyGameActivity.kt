package com.icarus.recycle_app.ui.study_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.icarus.recycle_app.R

class StudyGameActivity : AppCompatActivity() {


    private var lastBackPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_game)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - lastBackPressedTime < 2000) {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    lastBackPressedTime = System.currentTimeMillis()
                    Toast.makeText(this@StudyGameActivity, "뒤로 버튼을 한 번 더 누르면 뒤로 갑니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}