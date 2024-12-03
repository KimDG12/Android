package com.icarus.recycle_app.ui.study_game.adapters

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.icarus.recycle_app.ui.study_game.classes.CarutaCard
import com.icarus.recycle_app.databinding.CarutaCardItemBinding
import com.icarus.recycle_app.databinding.CarutaCardItemMarkBinding
import com.icarus.recycle_app.ui.study_game.classes.CardMark

class CardStackAdapter() : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    lateinit var showCards: MutableList<CarutaCard>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarutaCardItemMarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = showCards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = showCards[position]
        if (item.flag) {
            holder.itemView.visibility = View.GONE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
        } else {
            holder.itemView.visibility = View.VISIBLE
            holder.itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        holder.binding(item)
    }

    fun click(index: Int) {
        showCards[index].flag = true
        notifyItemChanged(index)
    }

    fun getPresentCardsSize(): Int{
        var count = 0
        for(card in showCards){
            if(!card.flag){
                count++
            }
        }
        return count
    }

    fun addCardItem(item: CarutaCard) {
        showCards.add(item)
        notifyItemChanged(showCards.size - 1)
    }

    fun checkingCard(id: String): Int {
        for (i in 0 until showCards.size) {
            if (showCards[i].id == id) {
                return i
            }
        }
        return -1
    }

    inner class ViewHolder(private val viewBinding: CarutaCardItemMarkBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun binding(item: CarutaCard) {
            if (item.color.startsWith("#") && item.color.length in 7..9) {

                val colorDrawable = ColorDrawable(Color.parseColor(item.color))
                Glide.with(viewBinding.root)
                    .load(colorDrawable)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                    .into(viewBinding.imageView)
            } else {
                Glide.with(viewBinding.root)
                    .load(item.color)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
                    .into(viewBinding.imageView)
            }

            CardMark.ITEMS[item.description]?.let {
                viewBinding.ivRecyclingSymbol.setColorFilter(it, PorterDuff.Mode.SRC_IN)
            }

            viewBinding.tvInnerText.text = item.description

        }
    }
}


