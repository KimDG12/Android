package com.icarus.recycle_app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.search.image.trash_request.TrashRequestActivity

class HomeAdapter(
    private val context: Context
) : RecyclerView.Adapter<HomeAdapter.TrashViewHolder>() {


    private var trashes: List<Trash> = mutableListOf()

    inner class TrashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cv_favorite_item, parent, false)
        return TrashViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrashViewHolder, position: Int) {
        val trash = trashes[position]

        Glide.with(holder.itemView)
            .load(trash.image)
            .into(holder.imageView)


        holder.imageView.setOnClickListener {
            val intent = Intent(context, TrashRequestActivity::class.java)
            intent.putExtra("trash", trash)
            intent.putExtra("type", 0)
            context.startActivity(intent)
        }

        holder.imageView.outlineProvider = ViewOutlineProvider.BACKGROUND
        holder.imageView.clipToOutline = true

    }

    override fun getItemCount(): Int {
        return trashes.size
    }

    fun updateData(newTrashes: List<Trash>) {
        this.trashes = newTrashes
        notifyDataSetChanged()
    }
}
