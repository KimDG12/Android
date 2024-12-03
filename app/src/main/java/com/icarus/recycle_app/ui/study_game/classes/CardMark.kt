package com.icarus.recycle_app.ui.study_game.classes

import android.graphics.Color

object CardMark {
    var ITEMS = mutableMapOf<String, Int>()

    init {
        addItem("페트", Color.rgb(255, 228, 0))
        addItem("플라스틱", Color.rgb(0, 84, 255))
        addItem("비닐류", Color.rgb(128, 65, 217))
        addItem("종이", Color.rgb(0, 0, 0))
        addItem("유리", Color.rgb(255, 187, 0))
        addItem("캔류", Color.rgb(166, 166, 166))
    }

    fun addItem(string: String, int: Int) {
        ITEMS[string] = int
    }
}