package com.icarus.recycle_app.ui.info.content.environment_tip

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R

class EnvironmentTipImageFragment(private val imageUri: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.cv_image_view, container, false)
        val imageView = view.findViewById<ImageView>(R.id.ivContent)
        Glide.with(requireContext()).load(imageUri).into(imageView)

        return view
    }

}