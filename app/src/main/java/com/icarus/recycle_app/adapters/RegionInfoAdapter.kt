package com.icarus.recycle_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.databinding.CvRegionInfoBinding
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.listener.OnItemClickListener

class RegionInfoAdapter(private val regionInfoList: List<RegionInfo>): RecyclerView.Adapter<RegionInfoAdapter.ViewHolder>() {

    /**
     * 라디오 버튼 클릭되었을 때 저장되는 id
     */
    var lastSelectedId = -1

    /**
     * 뷰홀더 레벨에서 접근 하는 리스너
     */
    private var listenerMap = mutableMapOf<Int, OnChangeRadioBtnListener?>()

    interface OnChangeRadioBtnListener{
        fun onClick()
    }

    init {
        lastSelectedId = regionInfoList[0].id

        for (item in regionInfoList) {
            listenerMap[item.id] = null
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegionInfoAdapter.ViewHolder {
        val binding =
            CvRegionInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RegionInfoAdapter.ViewHolder, position: Int) {
        holder.bind(regionInfoList[position])
    }

    override fun getItemCount(): Int = regionInfoList.size

    inner class ViewHolder(val binding: CvRegionInfoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RegionInfo) {
            binding.provinceName.text = item.provinceName
            binding.districtName.text = item.districtName
            binding.sectorName.text = item.sectorName
            binding.targetAreaName.text = item.targetAreaName


            listenerMap[item.id] = object: OnChangeRadioBtnListener {
                override fun onClick() {
                    binding.radioButton.isChecked = false
                }
            }

            // OnCheckedChangeListener를 제거 (뷰가 재사용되는 것을 대비)
            binding.radioButton.setOnCheckedChangeListener(null)

            // 아이템의 ID가 lastSelectedId와 동일한 경우 RadioButton을 체크합니다.
            binding.radioButton.isChecked = lastSelectedId == item.id

            // OnCheckedChangeListener를 다시 설정
            binding.radioButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // 이전 선택된 항목의 RadioButton을 해제합니다.
                    listenerMap[lastSelectedId]?.onClick()
                    // 현재 선택된 아이템의 ID를 저장합니다.
                    lastSelectedId = item.id
                }
            }

        }
    }

}