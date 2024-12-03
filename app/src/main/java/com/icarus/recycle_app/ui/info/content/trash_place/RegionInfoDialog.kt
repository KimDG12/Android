package com.icarus.recycle_app.ui.info.content.trash_place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.icarus.recycle_app.adapters.RegionInfoAdapter
import com.icarus.recycle_app.databinding.DialogTrashRegionOpenBinding
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.dto.RegionTrashPlaceInfo
import com.icarus.recycle_app.utils.ServerConnectHelper

class RegionInfoDialog(private val regionInfoList: List<RegionInfo>) : DialogFragment() {

    private var _binding: DialogTrashRegionOpenBinding? = null
    private val binding get() = _binding!!


    interface OnSelectBtnListener {
        fun onClick(lastSelectedId: Int)
    }

    var selectBtnListener: OnSelectBtnListener? = null


    override fun onStart() {
        super.onStart()
        val dialog = dialog

        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogTrashRegionOpenBinding.inflate(inflater, container, false)


        val adapter = RegionInfoAdapter(regionInfoList)

        binding.recyclerview.adapter = adapter


        binding.btnSelect.setOnClickListener {
            selectBtnListener?.onClick(adapter.lastSelectedId)
        }




        return binding.root
    }




}