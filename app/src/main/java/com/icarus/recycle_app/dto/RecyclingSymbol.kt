package com.icarus.recycle_app.dto

data class RecyclingSymbol(
    val innerText: String,
    val outerText: String,
    val color: Int,
    val detailItem: String,
    val detailMethod: String,
    val noRecycle: String,
    val cf: String
)
