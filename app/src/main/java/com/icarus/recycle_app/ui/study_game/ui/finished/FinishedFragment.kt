package com.icarus.recycle_app.ui.study_game.ui.finished

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.databinding.FragmentFinishedBinding

class FinishedFragment : Fragment() {

    companion object {
        fun newInstance() = FinishedFragment()
    }

    private var _viewBinding: FragmentFinishedBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var viewModel: FinishedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFinishedBinding.inflate(layoutInflater, container, false)






        return viewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}