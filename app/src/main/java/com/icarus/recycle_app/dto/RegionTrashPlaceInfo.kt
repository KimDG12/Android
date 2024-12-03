package com.icarus.recycle_app.dto

import com.google.gson.annotations.SerializedName


data class RegionTrashPlaceInfo(
    @SerializedName("번호")
    val id: Int,

    @SerializedName("시도명")
    val provinceName: String,

    @SerializedName("시군구명")
    val districtName: String,

    @SerializedName("관리구역명")
    val sectorName: String,

    @SerializedName("관리구역대상지역명")
    val targetAreaName: String,

    @SerializedName("배출장소유형")
    val disposalPlaceType: String,

    @SerializedName("배출장소")
    val disposalPlace: String,

    @SerializedName("생활쓰레기배출방법")
    val generalWasteDisposalMethod: String,

    @SerializedName("음식물쓰레기배출방법")
    val foodWasteDisposalMethod: String,

    @SerializedName("재활용품배출방법")
    val recyclableDisposalMethod: String,

    @SerializedName("생활쓰레기배출요일")
    val generalWasteDisposalDay: String,

    @SerializedName("음식물쓰레기배출요일")
    val foodWasteDisposalDay: String,

    @SerializedName("재활용품배출요일")
    val recyclableDisposalDay: String,

    @SerializedName("생활쓰레기배출시작시각")
    val generalWasteDisposalStartTime: String,

    @SerializedName("생활쓰레기배출종료시각")
    val generalWasteDisposalEndTime: String,

    @SerializedName("음식물쓰레기배출시작시각")
    val foodWasteDisposalStartTime: String,

    @SerializedName("음식물쓰레기배출종료시각")
    val foodWasteDisposalEndTime: String,

    @SerializedName("재활용품배출시작시각")
    val recyclableDisposalStartTime: String,

    @SerializedName("재활용품배출종료시각")
    val recyclableDisposalEndTime: String,

    @SerializedName("미수거일")
    val nonCollectionDay: String,

    @SerializedName("관리부서전화번호")
    val managementDepartmentPhone: String,

    @SerializedName("데이터기준일자")
    val dataStandardDate: String
)
