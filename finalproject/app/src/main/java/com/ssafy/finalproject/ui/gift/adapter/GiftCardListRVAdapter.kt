package com.ssafy.finalproject.ui.gift.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.databinding.ItemGiftCardBinding

class GiftCardListRVAdapter: ListAdapter<GiftCard, GiftCardListRVAdapter.ViewHolder>(IdComparator) {
    lateinit var itemClickListener: ItemClickListener
    private lateinit var context: Context

    interface ItemClickListener {
        fun onClick(view: View, data: GiftCard, position: Int)
    }

    companion object IdComparator : DiffUtil.ItemCallback<GiftCard>() {
        override fun areItemsTheSame(oldItem: GiftCard, newItem: GiftCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GiftCard, newItem: GiftCard): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemGiftCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GiftCard) {
            Glide.with(context)
                .load(data.imgUrl)
                .centerCrop()
                .into(binding.image)

            binding.card.setOnClickListener {
                itemClickListener.onClick(it, data, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GiftCardListRVAdapter.ViewHolder {
        context = parent.context
        val binding =
            ItemGiftCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val displayMetrics = context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val itemHeight = (screenHeight * 0.2).toInt()
        binding.root.layoutParams.height = itemHeight
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: GiftCardListRVAdapter.ViewHolder, position: Int) {
        val dto = getItem(position)
        holder.apply {
            bind(dto)
        }

    }
}