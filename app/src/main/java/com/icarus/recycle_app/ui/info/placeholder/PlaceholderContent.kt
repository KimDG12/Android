package com.icarus.recycle_app.ui.info.placeholder

import androidx.fragment.app.Fragment
import com.icarus.recycle_app.R
import com.icarus.recycle_app.ui.info.content.environment_tip.EnvironmentTipFragment
import com.icarus.recycle_app.ui.info.content.environmental_protection.EnvironmentGovermentFragment
import com.icarus.recycle_app.ui.info.content.recycling.RecyclingProcessFragment
import com.icarus.recycle_app.ui.info.content.trash_place.TrashPlaceFragment
import com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol.RecyclingSymbolFragment
import java.util.ArrayList
import java.util.HashMap

object PlaceholderContent {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    init {
        addItem(PlaceholderItem("0", "재활용 마크 설명", "재활용 마크에 대한 자세한 설명을 볼 수 있습니다.", RecyclingSymbolFragment(), R.drawable.info_1))
        addItem(PlaceholderItem(
            "1",
            "우리 지역 쓰레기 버리는 곳",
            "우리 지역의 쓰레기 버리는 곳을 볼 수 있습니다.",
            TrashPlaceFragment(),
            R.drawable.info_2
        ))
        addItem(PlaceholderItem(
            "2",
            "환경 관련 뉴스 안내",
            "환경 관련 인터넷 뉴스를 모아서 볼 수 있습니다.",
            EnvironmentTipFragment(),
            R.drawable.info_3
        ))
        addItem(PlaceholderItem(
            "3",
            "재활용 관련 공공기관 정보",
            "정부 및 지자체에서 주관하는 관련 캠페인 및 정보를 볼 수 있습니다.",
            EnvironmentGovermentFragment(),
            R.drawable.info_4
        ))
        addItem(PlaceholderItem(
            "4",
            "재활용품 재생 과정 알아보기",
            "쓰레기를 처리하고 재활용 과정을 단계별로 알아볼 수 있습니다.",
            RecyclingProcessFragment(),
            R.drawable.info_5_2
        ))
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    data class PlaceholderItem(
        val id: String,
        val title: String,
        val content: String,
        val fragment: Fragment,
        val info: Int
    )
}