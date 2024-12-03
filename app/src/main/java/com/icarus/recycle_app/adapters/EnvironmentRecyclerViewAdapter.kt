package com.icarus.recycle_app.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.CvCardviewEnvironmentTipInfoBinding
import com.icarus.recycle_app.dto.EnvironmentTip

class EnvironmentRecyclerViewAdapter(
    private val environmentTips: List<EnvironmentTip>?
): RecyclerView.Adapter<EnvironmentRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(pos: Int)
    }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnvironmentRecyclerViewAdapter.ViewHolder {
        val binding = CvCardviewEnvironmentTipInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EnvironmentRecyclerViewAdapter.ViewHolder,
        position: Int) {

        if (!environmentTips.isNullOrEmpty()) {
            holder.bind(environmentTips[position])
        }

        holder.itemView.setOnClickListener {
            listener.onItemClicked(position)
        }


    }

    override fun getItemCount(): Int {
        return environmentTips?.size ?: 0
    }

    inner class ViewHolder(val binding: CvCardviewEnvironmentTipInfoBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(item: EnvironmentTip) {
            binding.tvTitle.text = item.title

            binding.tvContent.text = if (item.body.length > 100) {
                item.body.substring(0, 50) + "..."
            } else {
                item.body
            }

            Glide.with(binding.root).load(item.image).into(binding.imageView)

        }
    }
}