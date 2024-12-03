package com.icarus.recycle_app.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.CvLoadingDialogBinding

class LoadingUtil(context: Context): Dialog(context) {
    private lateinit var binding: CvLoadingDialogBinding

    private var loadingTitle: String = "데이터 로딩중..."
    private var loadingInfo: String = "왜 로딩이 걸리나요?"
    private var loadingInfoContents: List<String> = mutableListOf(
        "1. 쾌적한 애플리케이션 환경을 위해 데이터를 서버에 보관합니다.",
        "2. 접속중인 사용자가 많응면 서버로부터 요청과 응답하는 시간이 걸릴 수 있습니다.",
        "3. 앞으로 더 나은 환경을 위해 노력하도록 하겠습니다.")
    private var viewList: List<View> = mutableListOf()
    private var arrowUp: Int = R.drawable.ic_arrow_up1_balck
    private var arrowDown: Int = R.drawable.ic_arrow_down1_black

    private var isInfoClicked = false

    private lateinit var recyclerAdapter: LoadingInfoRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CvLoadingDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initViewProperty()

        recyclerAdapter = LoadingInfoRecyclerAdapter(loadingInfoContents)

        binding.rvLoadingInfoContent.adapter = recyclerAdapter
        binding.rvLoadingInfoContent.setHasFixedSize(true)
    }

    private fun initListener() {
        binding.cvCenter.setOnClickListener {
            isInfoClicked = !isInfoClicked

            Log.d("loading_util", "Button clicked")

            if (isInfoClicked) {
                binding.ivLoadingInfoTitle.setBackgroundResource(arrowUp)
                binding.tvLoadingInfoTitle.visibility = View.VISIBLE
                binding.cvBottom.visibility = View.VISIBLE
            } else {
                binding.ivLoadingInfoTitle.setBackgroundResource(arrowDown)
                binding.tvLoadingInfoTitle.visibility = View.GONE
                binding.cvBottom.visibility = View.GONE
            }
        }
    }

    private fun initViewProperty() {
        setCancelable(false)

        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.tvLoadingInfoTitle.visibility = View.GONE
        binding.cvBottom.visibility = View.GONE
    }

    fun initUtil(
        loadingTitle: String,
        loadingInfo: String,
        loadingInfoContents: List<String>,
        viewList: List<View>,
        arrowDown: Int,
        arrowUp: Int
    ) {
        this.loadingTitle = loadingTitle
        this.loadingInfo = loadingInfo
        this.loadingInfoContents = loadingInfoContents
        this.viewList = viewList
        this.arrowDown = arrowDown
        this.arrowUp = arrowUp
    }

    fun changeVisibility(visibility: Int) {
        for (view in viewList) {
            view.visibility = visibility
        }
    }

    fun show(loadingTitle: String) {
        super.show()
        binding.tvLoadingMessage.text = loadingTitle
    }


    inner class LoadingInfoRecyclerAdapter(
        private val loadingInfoContents: List<String>
    ): RecyclerView.Adapter<LoadingInfoRecyclerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): LoadingInfoRecyclerAdapter.ViewHolder {
            val textView = TextView(parent.context).apply {
                val dp = 10f
                val px = (dp * parent.context.resources.displayMetrics.density + 0.5f).toInt()

                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(px * 2, px, px * 2, px)
                    // background = ContextCompat.getDrawable(parent.context, R.drawable.custom_circle_background_gray)
                    setPadding(px, px, px, px)
                }
            }

            return ViewHolder(textView)
        }

        override fun onBindViewHolder(
            holder: LoadingInfoRecyclerAdapter.ViewHolder,
            position: Int
        ) {
            holder.textView.text = loadingInfoContents[position]
        }

        override fun getItemCount(): Int = loadingInfoContents.size

        inner class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    }


}