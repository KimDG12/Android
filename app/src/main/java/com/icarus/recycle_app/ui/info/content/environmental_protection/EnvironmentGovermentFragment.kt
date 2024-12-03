package com.icarus.recycle_app.ui.info.content.environmental_protection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.EnvironmentRecyclerViewAdapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentGovermentBinding
import com.icarus.recycle_app.databinding.FragmentEnvironmentTipBinding
import com.icarus.recycle_app.databinding.FragmentEnvironmentalProtectionBinding
import com.icarus.recycle_app.dto.EnvironmentTip
import com.icarus.recycle_app.ui.info.content.environment_tip.EnvironmentTipContentFragment
import com.icarus.recycle_app.utils.ServerConnectHelper

class EnvironmentGovermentFragment : Fragment() {
    private var _binding : FragmentEnvironmentGovermentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EnvironmentGovermentViewModel

    companion object {
        fun newInstance() = EnvironmentGovermentFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnvironmentGovermentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[EnvironmentGovermentViewModel::class.java]



        val serverConnectHelper = ServerConnectHelper()
        serverConnectHelper.requestEnvironmentTip = object: ServerConnectHelper.RequestEnvironment {
            override fun onSuccess(environmentTipList: List<EnvironmentTip>) {
                Log.d("testx", environmentTipList.toString())
                viewModel.environmentTipList.value = environmentTipList
                Log.d("asd", "성공")

            }

            override fun onFailure() {
                Log.d("asd", "실패")
            }

        }

        viewModel.environmentTipList.observe(requireActivity()) {
            if (viewModel.environmentTipList.value?.isNotEmpty() == true) {
                val adapter = EnvironmentRecyclerViewAdapter(viewModel.environmentTipList.value)

                Log.d("test", viewModel.environmentTipList.value.toString())

                adapter.listener = object : EnvironmentRecyclerViewAdapter.OnItemClickListener {
                    override fun onItemClicked(pos: Int) {

                        viewModel.isClickedPosition.value = pos

                        val transaction = parentFragmentManager.beginTransaction()
                        transaction.setCustomAnimations(R.anim.slide_in_right, 0, 0, 0)
                        transaction.replace(R.id.flFragment, EnvironmentGovermentContentFragment())
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                }

                binding.rvEnvironmentTip.adapter = adapter
                binding.rvEnvironmentTip.setHasFixedSize(true)
            }
        }

        Log.d("asd", viewModel.environmentTipList.value?.size.toString())
        if (viewModel.environmentTipList.value.isNullOrEmpty()) {
            Log.d("asd", "get")
            serverConnectHelper.getGovermentBlog()
        }


        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}