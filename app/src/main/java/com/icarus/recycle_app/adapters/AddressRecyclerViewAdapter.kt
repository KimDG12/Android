package com.icarus.recycle_app.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.CvAddressSelectBinding
import com.icarus.recycle_app.databinding.CvCardviewEnvironmentTipInfoBinding
import com.icarus.recycle_app.dto.Address
import com.icarus.recycle_app.dto.EnvironmentTip

class AddressRecyclerViewAdapter(
): RecyclerView.Adapter<AddressRecyclerViewAdapter.ViewHolder>() {

    private lateinit var addressList: List<Address>
    @SuppressLint("NotifyDataSetChanged")
    fun setAddressList(addressList: List<Address>) {
        this.addressList = addressList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(clickAddress: Address)
    }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressRecyclerViewAdapter.ViewHolder {
        val binding = CvAddressSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AddressRecyclerViewAdapter.ViewHolder,
        position: Int) {

        holder.bind(addressList[position])


    }

    override fun getItemCount(): Int {
        return addressList.size
    }

    inner class ViewHolder(val binding: CvAddressSelectBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(item: Address) {
            binding.tvAddress.text = item.address

            if (item.selected) binding.tvSelected.visibility = VISIBLE else binding.tvSelected.visibility = INVISIBLE

            binding.root.setOnClickListener {
                listener.onItemClicked(item)
            }


        }
    }
}