package com.icarus.recycle_app.ui.study_game.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentStartBinding
import com.icarus.recycle_app.ui.study_game.ui.in_progress.InProgressFragment

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private var _viewBinding: FragmentStartBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentStartBinding.inflate(layoutInflater, container, false)


        viewBinding.btnStart.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment_main, InProgressFragment())
                .addToBackStack(null)
                .commit()
        }

        return viewBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}