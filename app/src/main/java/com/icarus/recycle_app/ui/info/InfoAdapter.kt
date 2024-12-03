package com.icarus.recycle_app.ui.info

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

import com.icarus.recycle_app.ui.info.placeholder.PlaceholderContent.PlaceholderItem
import com.icarus.recycle_app.databinding.ItemInfoBinding

class InfoAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    interface ButtonListener {
        fun onClick(id: String)
    }

    lateinit var buttonListener: ButtonListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 홀더에서 bind
        holder.bind(position)

        // 버튼을 누르면 해당 프래그먼트 반환
        holder.binding.btnAction.setOnClickListener {
            buttonListener.onClick(values[position].id)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            this.binding.tvTitle.text = values[position].title
            this.binding.tvContent.text = values[position].content

            Glide.with(this.binding.root)
                .load(values[position].info)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .into(this.binding.ivImage)

            this.binding.ivImage.outlineProvider = ViewOutlineProvider.BACKGROUND
            this.binding.ivImage.clipToOutline = true
        }
    }

}