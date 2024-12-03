package com.icarus.recycle_app.ui.info.content.recycling

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.ProcessRecyclerAdapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentalProtectionBinding
import com.icarus.recycle_app.dto.RecycleProcess

class RecyclingProcessFragment : Fragment() {

    private var _binding : FragmentEnvironmentalProtectionBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = RecyclingProcessFragment()
    }

    private lateinit var viewModel: EnvironmentalProtectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEnvironmentalProtectionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[EnvironmentalProtectionViewModel::class.java]

        val items = listOf(


            RecycleProcess("금속 캔", listOf(
                ContextCompat.getDrawable(requireContext(), R.drawable.ironcan_process_1)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.ironcan_process_2)!!,
                    ContextCompat.getDrawable(requireContext(), R.drawable.ironcan_process_3)!!)),


            RecycleProcess("종이팩", listOf(
                ContextCompat.getDrawable(requireContext(), R.drawable.paper_process_1)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.paper_process_2)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.paper_process_3)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.paper_process_4)!!)),


            RecycleProcess("페트병", listOf(
                ContextCompat.getDrawable(requireContext(), R.drawable.pet_process_1)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.pet_process_2)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.pet_process_3)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.pet_process_4)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.pet_process_5)!!,)),



            RecycleProcess("유리병", listOf(
                ContextCompat.getDrawable(requireContext(), R.drawable.glass_process_1)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.glass_process_2)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.glass_process_3)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.glass_process_4)!!)),


            RecycleProcess("비닐", listOf(
                ContextCompat.getDrawable(requireContext(), R.drawable.vinyl_process_1)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.vinyl_process_2)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.vinyl_process_3)!!)))



        val adapter = ProcessRecyclerAdapter(items, childFragmentManager, lifecycle)
        binding.recyclerview.adapter = adapter




        binding.fab.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("참고")
            builder.setMessage("한국포장재재활용사업공제조합과 세종특별자치시 - 자원 순환\n한국폐기물협회와 한화토탈에너지스 케미인 공식 블로그를 참조하였습니다.")
            builder.setPositiveButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}