package com.icarus.recycle_app.ui.info

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.ui.info.InfoAdapter.ButtonListener
import com.icarus.recycle_app.ui.info.content.InfoContentActivity
import com.icarus.recycle_app.ui.info.placeholder.PlaceholderContent


/**
 * A fragment representing a list of Items.
 */
class InfoFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        // 리사이클러뷰 설정
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        val adapter = InfoAdapter(PlaceholderContent.ITEMS)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // 설명 보러기가기 버튼을 누르면 실행되는 리스너
        adapter.buttonListener = object: ButtonListener{
            override fun onClick(id: String) {
                val intent = Intent(requireContext(), InfoContentActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        }

        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}