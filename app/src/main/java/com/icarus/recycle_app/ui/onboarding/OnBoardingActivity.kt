package com.icarus.recycle_app.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.icarus.recycle_app.adapters.ViewPager2Adapter
import com.icarus.recycle_app.databinding.ActivityOnboardingBinding
import com.icarus.recycle_app.ui.onboarding.onboardings.OnBoardingFirstFragment
import com.icarus.recycle_app.ui.onboarding.onboardings.OnBoardingSecondFragment
import com.icarus.recycle_app.ui.onboarding.onboardings.OnBoardingThirdFragment

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // supportActionBar?.hide()

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFirstFragment(),
            OnBoardingSecondFragment(),
            OnBoardingThirdFragment()
        )

        val viewPager2Adapter = ViewPager2Adapter(
            fragmentList,
            this.supportFragmentManager,
            lifecycle
        )

        binding.viewPager2.adapter = viewPager2Adapter


    }
}