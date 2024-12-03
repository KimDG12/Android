package com.icarus.recycle_app.ui.study_game.ui.in_progress

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.study_game.classes.CarutaCard
import com.icarus.recycle_app.ui.study_game.utils.CardCreator
import com.icarus.recycle_app.ui.study_game.utils.TimeFormatConvertor
import com.icarus.recycle_app.utils.ServerConnectHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class InProgressViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _elapsedTime = MutableLiveData("00:00.00")
    val elapsedTime: LiveData<String> get() = _elapsedTime
    private var rawTime = 0L

    private val _selectedCard = MutableLiveData<CarutaCard>()
    val selectedCard: LiveData<CarutaCard> get() = _selectedCard

    private val _showCards = MutableLiveData<MutableList<CarutaCard>>()
    val showCards: LiveData<MutableList<CarutaCard>> get() = _showCards

    private val _selectCards = MutableLiveData<MutableList<CarutaCard>>()
    val selectCards: LiveData<MutableList<CarutaCard>> get() = _selectCards


    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score


    fun setCardList(trashes: List<Trash>) {

        val showCards = mutableListOf<CarutaCard>()
        val selectCards = mutableListOf<CarutaCard>()

        Log.d("testx", "쓰레기 받아서 리스트 생성 완료")

        for (trash in trashes) {
            Log.d("testx", trash.toString())

            trash.type = trash.type.replace("분류: ", "")
            val upCard = CarutaCard(description = trash.type)
            showCards.add(upCard)

            val downCard = CarutaCard(source = trash.image)
            selectCards.add(downCard)
        }

        val cardCreator = CardCreator(trashes.size, showCards, selectCards)
        _showCards.value = cardCreator.getConversionShowCards()
        _selectCards.value = cardCreator.getConversionSelectCards()
        selectRandomCard()
    }

    fun addScore() {
        _score.value = _score.value?.plus(10)
    }

    fun subScore() {
        _score.value = _score.value?.plus(-10)
    }


    fun setCardListRandom(count: Int) {
        val cardCreator = CardCreator(count)
        _showCards.value = cardCreator.getConversionShowCards()
        _selectCards.value = cardCreator.getConversionSelectCards()
        selectRandomCard()
    }

    fun selectRandomCard() {
        if (_showCards.value?.isNotEmpty() == true) {
            val randomCard = _showCards.value?.random()
            randomCard?.let {
                _selectedCard.postValue(it)
                _showCards.value?.remove(it)
            }

        }
    }

    fun startTimer() {
        uiScope.launch {
            while (true) {
                delay(1)
                rawTime += 2L
                _elapsedTime.postValue(TimeFormatConvertor.convertToTimeFormat(rawTime))

                if (rawTime % 10000 == 0L) {
                    selectRandomCard()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}