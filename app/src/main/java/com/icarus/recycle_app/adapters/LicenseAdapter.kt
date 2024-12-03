package com.icarus.recycle_app.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.core.view.marginTop
import com.icarus.recycle_app.R


class LicenseAdapter(
    private val context: Context,
    private val titles: MutableList<String>,
    private val items: MutableMap<String, String>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return titles.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return titles[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return items[titles[groupPosition]]!!
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    @SuppressLint("InflateParams")
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val groupTitle = getGroup(groupPosition) as String
        val view: View
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null)
        } else {
            view = convertView
        }
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val leftPadding = 100 * context.resources.displayMetrics.density.toInt() // 100dp to pixels
        view.setPadding(leftPadding, 100, 100, 100)
        textView.text = groupTitle
        return view
    }

    @SuppressLint("InflateParams")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val item = getChild(groupPosition, childPosition) as String
        val view: View
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_list_item_1, null)
        } else {
            view = convertView
        }
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = item
        view.setPadding(60, 60, 60, 60)
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}


