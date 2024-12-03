package com.icarus.recycle_app.ui.study_game.utils

import android.util.Log
import com.icarus.recycle_app.ui.study_game.classes.CarutaCard
import kotlin.random.Random

class CardCreator(
    private val count: Int,
    private var showCards: MutableList<CarutaCard>? = null,
    private var selectCards: MutableList<CarutaCard>? = null
) {

    private var testMode: Boolean = false

    init {
        testMode = showCards.isNullOrEmpty() && selectCards.isNullOrEmpty()
        conversionCardList()
    }

    private fun conversionCardList() {
        Log.d("testx", testMode.toString())
        if (testMode) {
            showCards = mutableListOf()
            selectCards = mutableListOf()

            for (i: Int in 0 until count) {
                val randomColor = getRandomColor()
                val showCard = CarutaCard(i.toString(), i.toString(), i.toString(), randomColor)
                val selectCard = CarutaCard(i.toString(), i.toString(), i.toString(), randomColor)

                showCards?.add(showCard)
                selectCards?.add(selectCard)
            }
        } else {
            for (i: Int in 0 until count) {
                val randomColor = getRandomColor()

                showCards?.let {
                    it[i].id = i.toString()
                    it[i].color = randomColor
                }
                selectCards?.let {
                    it[i].id = i.toString()
                    it[i].color = randomColor
                }
            }
        }
    }

    fun getConversionCardLists(): List<MutableList<CarutaCard>> {
        return listOf(showCards!!, selectCards!!)
    }

    fun getConversionShowCards() : MutableList<CarutaCard> {
        showCards!!.shuffle()

        return showCards!!
    }

    fun getConversionSelectCards() : MutableList<CarutaCard> {
        selectCards!!.shuffle()

        return selectCards!!
    }

    private fun getRandomColor(): String {
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)
        return String.format("#%02X%02X%02X", r, g, b)
    }








}