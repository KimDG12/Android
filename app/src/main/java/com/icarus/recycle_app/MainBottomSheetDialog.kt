package com.icarus.recycle_app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainBottomSheetDialog : BottomSheetDialogFragment() {
    private var mListener: BottomSheetListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.dialog_main_fragment_bottom_sheet, container, false)
        val btnCamera = v.findViewById<LinearLayout>(R.id.ll_camera)
        val btnGallery = v.findViewById<LinearLayout>(R.id.ll_gallery)
        btnCamera.setOnClickListener { v1: View? ->
            mListener!!.onButtonClicked("click_btn_camera")
            dismiss()
        }
        btnGallery.setOnClickListener { v1: View? ->
            mListener!!.onButtonClicked("click_btn_gallery")
            dismiss()
        }
        return v
    }

    interface BottomSheetListener {
        fun onButtonClicked(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement BottomSheetListener"
            )
        }
    }
}