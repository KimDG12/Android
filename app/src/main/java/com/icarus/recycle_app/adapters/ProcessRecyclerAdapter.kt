package com.icarus.recycle_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.databinding.CvProcessItemBinding
import com.icarus.recycle_app.dto.RecycleProcess
import com.icarus.recycle_app.ui.info.content.recycling.ProcessImageFragment

class ProcessRecyclerAdapter(private val items: List<RecycleProcess>, private val fragmentManager: FragmentManager, private val lifecycle: androidx.lifecycle.Lifecycle) : RecyclerView.Adapter<ProcessRecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(pos: Int)
    }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProcessRecyclerAdapter.ViewHolder {
        val binding = CvProcessItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProcessRecyclerAdapter.ViewHolder,
        position: Int) {

        holder.bind(items[position])


    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: CvProcessItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: RecycleProcess) {
            val fragmentList = ArrayList<Fragment>()

            for (drawable in item.sources) {
                fragmentList.add(ProcessImageFragment(drawable))
            }

            val viewPagerAdapter = ViewPager2Adapter(fragmentList, fragmentManager, lifecycle)
            binding.vpTitleImage.adapter = viewPagerAdapter
            binding.indicator.setViewPager(binding.vpTitleImage)

            binding.tvState.text = item.type


        }
    }
}
