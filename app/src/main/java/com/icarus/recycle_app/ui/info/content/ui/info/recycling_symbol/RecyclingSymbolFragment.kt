package com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol

import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.icarus.recycle_app.adapters.ExpandableListAdapter
import com.icarus.recycle_app.databinding.FragmentRecyclingSymbolBinding


/**
 * 쓰레기 재활용 마크 안내
 */
class RecyclingSymbolFragment : Fragment() {

    private var _binding : FragmentRecyclingSymbolBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = RecyclingSymbolFragment()
    }

    private lateinit var viewModel: RecyclingSymbolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecyclingSymbolViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclingSymbolBinding.inflate(inflater, container, false)

        val adapter = ExpandableListAdapter(requireContext(), RecyclingSymbolContent.ITEMS)

        val newDisplay: Display = requireActivity().windowManager.getDefaultDisplay()
        val width = newDisplay.width

        binding.elvRecyclingSymbol.setAdapter(adapter)
        binding.elvRecyclingSymbol.setIndicatorBounds(width-5000, width);



        return binding.root
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}