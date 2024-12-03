package com.icarus.recycle_app.ui.info.content.recycling

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.icarus.recycle_app.AppManager.dpToPx
import com.icarus.recycle_app.R

class ProcessImageFragment(private val drawable: Drawable) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.cv_image_view, container, false)
        val imageView = view.findViewById<ImageView>(R.id.ivContent)
        Glide.with(requireContext())
            .load(drawable)
            .apply(RequestOptions().transform(RoundedCorners(requireContext().dpToPx(30))))
            .into(imageView)

        return view
    }

}