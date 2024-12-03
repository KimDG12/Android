package com.icarus.recycle_app.ui.setting

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)


        val dialog = AlertDialog.Builder(requireContext())
        dialog.setPositiveButton("확인") { sDialog, _ -> sDialog?.dismiss() }


        binding.clLicense.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), LicenseActivity::class.java))
        }

        binding.clTeamInfo.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), DeveloperInfoActivity::class.java))
        }

        binding.clAppInfo.setOnClickListener {
            dialog.setMessage("리본 : AI 이미지 인식 재활용 안내\n\n인공지능 쓰레기 재활용 안내 애플리케이션\n\nv0.1")
            dialog.show()
        }
        binding.clShowFavorite.setOnClickListener {
            startActivity(Intent(requireContext(),BookmarkListActivity::class.java))
        }

        binding.clEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("ymail3@naver.com")) // 수신인 주소
                putExtra(Intent.EXTRA_SUBJECT, "재활용 이미지 인식 앱 문의 사항") // 이메일 제목
                putExtra(Intent.EXTRA_TEXT, "문제 발생 시간 및 스크린샷 등을 첨부하여 자세히 설명해주시면 문제 해결에 원활히 진행 할 수 있습니다.") // 이메일 본문
            }

            // 이메일 앱이 설치되어 있는지 확인
            if (emailIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(emailIntent)
            } else {
                Toast.makeText(this@SettingFragment.requireContext(), "이메일 앱이 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.clBudgetCycle.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", requireActivity().packageName, null)
            intent.data = uri
            startActivity(intent)
        }


        binding.clTrashAlarm.setOnClickListener {
            Toast.makeText(requireContext(), "추후 추가 예정입니다.", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}