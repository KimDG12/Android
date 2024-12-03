package com.icarus.recycle_app.dto

import com.google.gson.annotations.SerializedName


data class EnvironmentTip(

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("body")
    val body: String,

    @SerializedName("reporter")
    val reporter: String,

    @SerializedName("image")
    val image: String
)
