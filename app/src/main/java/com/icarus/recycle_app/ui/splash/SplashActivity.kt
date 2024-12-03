package com.icarus.recycle_app.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.MainActivity
import com.icarus.recycle_app.R
import com.icarus.recycle_app.ui.onboarding.OnBoardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppManager.init(applicationContext)

        supportActionBar?.hide()

        if(AppManager.getStartInit()==true){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            }, 3000)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                AppManager.startInit()
                startActivity(Intent(this, OnBoardingActivity::class.java))
                finish()

                // 기존 접속자 도움말 OnBoarding 화면으로 넘어가지 않도록 수정해야함
            }, 3000)
        }

    }
}