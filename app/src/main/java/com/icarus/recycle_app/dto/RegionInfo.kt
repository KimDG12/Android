package com.icarus.recycle_app.dto

import com.google.gson.annotations.SerializedName

data class RegionInfo(
    @SerializedName("번호")
    val id: Int,

    @SerializedName("시도명")
    val provinceName: String,

    @SerializedName("시군구명")
    val districtName: String,

    @SerializedName("관리구역명")
    val sectorName: String,

    @SerializedName("관리구역대상지역명")
    val targetAreaName: String
)