package com.omerakkoyun.mybaseapp1.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerakkoyun.mybaseapp1.databinding.ItemCoinViewBinding
import com.omerakkoyun.mybaseapp1.models.cryptoResponse.Data

class CoinRecyclerViewAdapter (private val listener: ItemClickListener): RecyclerView.Adapter<CoinRecyclerViewAdapter.ViewHolder>() {

    private var coins = emptyList<Data>()

    fun setCoinDataList(list: List<Data>){
        coins = list
        notifyDataSetChanged()
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

    override fun getItemCount() = coins.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(listener,coins[position])
    }


}