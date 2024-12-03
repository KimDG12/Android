package com.icarus.recycle_app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icarus.recycle_app.databinding.ItemCategoryBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.trash_request.TrashRequestActivity

class CategoryResultAdapter(private val trashes: List<Trash>, private val context: Context) : RecyclerView.Adapter<CategoryResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trash = trashes[position]
        holder.binding.tvTitle.text = trash.name
        holder.binding.ivImage.outlineProvider = ViewOutlineProvider.BACKGROUND
        holder.binding.ivImage.clipToOutline = true


        Glide.with(context)
                .load(trash.image)
                .into(holder.binding.ivImage)
        holder.binding.llCategory.setOnClickListener {
            val intent = Intent(context, TrashRequestActivity::class.java)
            intent.putExtra("trash", trash)
            intent.putExtra("type",0)
            // FLAG_ACTIVITY_NEW_TASK 플래그 추가
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return trashes.size
    }

    inner class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}