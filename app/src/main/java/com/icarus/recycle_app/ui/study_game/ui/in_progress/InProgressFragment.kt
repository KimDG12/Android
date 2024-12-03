package com.icarus.recycle_app.ui.study_game.ui.in_progress


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.icarus.recycle_app.databinding.FragmentInProgressBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.study_game.views.CountdownDialog
import com.icarus.recycle_app.ui.study_game.adapters.CardGridAdapter
import com.icarus.recycle_app.ui.study_game.adapters.CardStackAdapter
import com.icarus.recycle_app.ui.study_game.classes.CarutaCard
import com.icarus.recycle_app.ui.study_game.utils.CardCreator
import com.icarus.recycle_app.utils.ServerConnectHelper

class InProgressFragment : Fragment() {

    companion object {
        fun newInstance() = InProgressFragment()
    }

    private var _viewBinding: FragmentInProgressBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var viewModel: InProgressViewModel

    private val cardStackAdapter = CardStackAdapter()
    private var cardGridAdapter: CardGridAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentInProgressBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[InProgressViewModel::class.java]

        cardStackAdapter.showCards = mutableListOf()
        viewBinding.rvCardStack.adapter = cardStackAdapter

        viewModel.selectedCard.observe(viewLifecycleOwner) {
            cardStackAdapter.addCardItem(it)
            if(cardStackAdapter.getPresentCardsSize() == 5)
                Toast.makeText(requireContext(),"패배하셨습니다",Toast.LENGTH_SHORT).show()
            Log.d("testx", cardStackAdapter.getPresentCardsSize().toString())
        }

        viewModel.score.observe(viewLifecycleOwner) {
            viewBinding.inProgressState.tvCurrentScore.text = it.toString()
        }


        val serverConnectHelper = ServerConnectHelper()
        val count = 30

        serverConnectHelper.requestTrashesRandom = object: ServerConnectHelper.RequestTrashes {
            override fun onSuccess(trashes: List<Trash>) {
                Log.d("testx", "통신 성공")
                viewModel.setCardList(trashes)

                viewModel.selectCards.value?.let {
                    cardGridAdapter = CardGridAdapter(it)
                    cardGridAdapter?.listener = object: CardGridAdapter.OnItemClickListener {
                        override fun onClick(id: String, position: Int) {
                            val checkedIndex = cardStackAdapter.checkingCard(id)
                            if (checkedIndex != -1) {
                                cardGridAdapter?.changeItemVisible(position)
                                viewModel.addScore()
                                cardStackAdapter.click(checkedIndex)
                                viewModel.selectRandomCard()
                            } else {
                                viewModel.subScore()
                                Toast.makeText(requireContext(), "틀렸습니다!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    viewBinding.rvCardGrid.adapter = cardGridAdapter
                    viewBinding.rvCardGrid.layoutManager = GridLayoutManager(requireContext(), 4)
                }

                showDialog()

            }

            override fun onFailure() {
                Log.d("testx", "통신 실패")
            }
        }

        Log.d("testx", "init 한번")
        serverConnectHelper.getRandomTrashes(count)

        return viewBinding.root
    }

    private fun showDialog() {
        val startCountdownDialog = CountdownDialog(requireContext())
        startCountdownDialog.countDownListener = object : CountdownDialog.CountDownListener {
            override fun onComplete() {
                initProgress()
            }
        }
        startCountdownDialog.show()
        Log.d("testx", "다이얼로그 쇼 끝까지")
    }



    private fun initProgress() {
        viewModel.startTimer()
        viewModel.elapsedTime.observe(viewLifecycleOwner) { timeText ->
            viewBinding.inProgressState.tvTime.text = timeText
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}