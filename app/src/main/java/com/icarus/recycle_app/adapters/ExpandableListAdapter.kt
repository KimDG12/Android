package com.icarus.recycle_app.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R
import com.icarus.recycle_app.dto.RecyclingSymbol
import com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol.RecyclingSymbolFragment

class ExpandableListAdapter (
    private val context: Context,
    private val items: List<RecyclingSymbol>
    ) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int = items.size

    override fun getChildrenCount(groupPosition: Int): Int = 1

    override fun getGroup(groupPosition: Int): Any = items[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any = items[childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        pos: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.cv_expandable_list_group, parent, false)
        }



        view?.let {
            val title = it.findViewById<TextView>(R.id.tvTitle)
            title.text = items[pos].innerText + " " + items[pos].outerText + "에 대한 정보"


            val innerText = it.findViewById<TextView>(R.id.tvInnerText)
            innerText.text = items[pos].innerText

            val outerText = it.findViewById<TextView>(R.id.tvOuterText)
            outerText.text = items[pos].outerText

            val imageView = it.findViewById<ImageView>(R.id.ivRecyclingSymbol)
            imageView.setColorFilter(items[pos].color, PorterDuff.Mode.SRC_IN)


            val ivArrowMark = view.findViewById<TextView>(R.id.ibArrow)

            if (isExpanded) {
                ivArrowMark.setBackgroundResource(R.drawable.ic_arrow_up1_balck)
            } else {
                ivArrowMark.setBackgroundResource(R.drawable.ic_arrow_down1_black)
            }

        }

        return view!!
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup?
    ):  View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.cv_expandable_list_child, parent, false)
        }

        view?.let {
            val detailItem = it.findViewById<TextView>(R.id.tvDetailItem)
            detailItem.text = items[groupPosition].detailItem

            val detailMethod = it.findViewById<TextView>(R.id.tvDetailMethod)
            detailMethod.text = items[groupPosition].detailMethod

            val noRecycle = it.findViewById<TextView>(R.id.tvNoRecycle)
            noRecycle.text = items[groupPosition].noRecycle

            val cf = it.findViewById<TextView>(R.id.tvCf)
            cf.text = items[groupPosition].cf

        }

        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}



