package com.icarus.recycle_app.ui.onboarding.onboardings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.AppManager
import com.icarus.recycle_app.MainActivity
import com.icarus.recycle_app.databinding.FragmentOnboardingThirdBinding

class OnBoardingThirdFragment : Fragment() {

    private var _binding: FragmentOnboardingThirdBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingThirdBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnNext.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finish()

            // 기존 접속자 도움말 OnBoarding 화면으로 넘어가지 않도록 수정해야함
        }


        return root
    }

}