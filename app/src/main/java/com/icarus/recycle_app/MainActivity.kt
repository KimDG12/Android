package com.icarus.recycle_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.icarus.recycle_app.databinding.ActivityMainBinding
import com.icarus.recycle_app.ui.onboarding.OnBoardingActivity
import com.icarus.recycle_app.ui.search.SearchActivity
import java.util.UUID

class MainActivity : AppCompatActivity(), MainBottomSheetDialog.BottomSheetListener {

    private lateinit var binding: ActivityMainBinding

    /**
     * 뒤로가기 버튼을 두 번 감지하기 위한 플래그 변수
     */
    private var backPressedTwice = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_info,R.id.navigation_current_situation,R.id.navigation_setting
//            )
//        )
        // setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // 리스너 등록
        initListener()

        // 앱 매니저 등록
        initAppManager()

        Log.d("asd", AppManager.getUid().toString())

    }

    /**
     * 앱 매니저 등록
     */
    private fun initAppManager() {
        // 스마트폰 내부에 uid가 없다면 생성
        val sp: SharedPreferences = this.getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val uid = sp.getString("user_id", null)

        if (uid == null) {
            // 미리 저장된 uid가 없을 경우 새로운 uid를 만들고 저장
            val newUid = UUID.randomUUID().toString()
            sp.edit().putString("user_id", newUid).apply()
            AppManager.setUid(newUid)
        } else {
            // 이미 저장된 uid가 있을경우 uid를 불러옴
            AppManager.setUid(uid)
        }
    }


    private fun generateRandomUUID(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }

    /**
     * 메인 액티비티 레벨의 리스너 추가
     */
    private fun initListener() {
        // 사진 업로드 버튼
        binding.fab.setOnClickListener {
            val bottomSheetDialog = MainBottomSheetDialog()
            bottomSheetDialog.show(supportFragmentManager, "example")

        }


        // 뒤로가기 감지
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedTwice) {
                    finish()
                } else {
                    backPressedTwice = true
                    Toast.makeText(this@MainActivity, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                    // 2초 내에 두 번째 뒤로가기 버튼을 눌러야 함
                    // 여기에서 2초 내에 누르지 않으면 다시 false로 설정하여 재시작
                    window.decorView.postDelayed({ backPressedTwice = false }, 2000)
                }
            }
        })


    }

    /**
     * 사진 버튼을 눌렀을 때 카메라, 갤러리, 바코드 버튼에 따라 다른 기능을 동작
     */
    override fun onButtonClicked(text: String?) {
        // 버튼이 클릭되었을 때의 동작을 구현합니다.
        // 이 메서드는 MainBottomSheetDialog 내부에서 호출될 것입니다.
        // 예시로 로그 출력을 해보겠습니다.
        Log.d("MyArg", "Button clicked with text: $text")

        when (text) {
            "click_btn_camera" -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("click_btn", 0)
                startActivity(intent)
            }
            "click_btn_gallery" -> {
                val intent = Intent(this, SearchActivity::class.java)
                intent.putExtra("click_btn", 1)
                startActivity(intent)
            }
        }

    }

}
