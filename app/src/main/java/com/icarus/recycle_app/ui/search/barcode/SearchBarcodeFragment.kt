package com.icarus.recycle_app.ui.search.barcode

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R

class SearchBarcodeFragment : Fragment() {

    companion object {
        fun newInstance() = SearchBarcodeFragment()
    }

    private lateinit var viewModel: SearchBarcodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_barcode, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchBarcodeViewModel::class.java)
        // TODO: Use the ViewModel
    }



}