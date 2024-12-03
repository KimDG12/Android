package com.icarus.recycle_app.ui.info.content.environment_tip

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.EnvironmentRecyclerViewAdapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentTipBinding
import com.icarus.recycle_app.dto.EnvironmentTip
import com.icarus.recycle_app.utils.ServerConnectHelper
import com.icarus.recycle_app.ui.info.content.InfoContentActivity as InfoContentActivity1

class EnvironmentTipFragment : Fragment() {

    private var _binding : FragmentEnvironmentTipBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EnvironmentTipViewModel

    companion object {
        fun newInstance() = EnvironmentTipFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnvironmentTipBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[EnvironmentTipViewModel::class.java]



        val serverConnectHelper = ServerConnectHelper()
        serverConnectHelper.requestEnvironmentTip = object: ServerConnectHelper.RequestEnvironment {
            override fun onSuccess(environmentTipList: List<EnvironmentTip>) {
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

                adapter.listener = object : EnvironmentRecyclerViewAdapter.OnItemClickListener {
                    override fun onItemClicked(pos: Int) {

                        viewModel.isClickedPosition.value = pos

                        val transaction = parentFragmentManager.beginTransaction()
                        transaction.setCustomAnimations(R.anim.slide_in_right, 0, 0, 0)
                        transaction.replace(R.id.flFragment, EnvironmentTipContentFragment())
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
            serverConnectHelper.getEnvironmentTip()
        }


        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}