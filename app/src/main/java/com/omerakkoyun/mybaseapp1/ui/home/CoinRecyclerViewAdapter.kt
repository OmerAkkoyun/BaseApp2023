package com.omerakkoyun.mybaseapp1.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.mybaseapp1.databinding.ItemCoinViewBinding
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.Data

class CoinRecyclerViewAdapter (private val listener: ItemClickListener): PagingDataAdapter<Data,CoinRecyclerViewAdapter.ViewHolder>(DIFF_UTIL) {

    companion object{
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Data>(){
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                // views id same?
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                // view content's is same?
                return  oldItem == newItem
            }

        }
    }


    class ViewHolder (private val binding:ItemCoinViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ItemClickListener,coin:Data){
            binding.onItemClickListener = listener
            binding.coin = coin
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemCoinViewBinding.inflate(layoutInflater,parent,false))
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = getItem(position)
        if (coin != null) {
            holder.bind(listener,coin)
        }
    }


}