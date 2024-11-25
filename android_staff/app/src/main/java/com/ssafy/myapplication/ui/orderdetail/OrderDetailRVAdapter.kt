package com.ssafy.myapplication.ui.orderdetail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.data.model.dto.OrderDetailInfo
import com.ssafy.myapplication.R
import com.ssafy.myapplication.databinding.ItemOrderDetailBinding
import com.ssafy.myapplication.util.CommonUtils

class OrderDetailRVAdapter: ListAdapter<OrderDetailInfo, OrderDetailRVAdapter.ViewHolder>(IdComparator) {
    private lateinit var context: Context

    companion object IdComparator : DiffUtil.ItemCallback<OrderDetailInfo>() {
        override fun areItemsTheSame(oldItem: OrderDetailInfo, newItem: OrderDetailInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OrderDetailInfo, newItem: OrderDetailInfo): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemOrderDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: OrderDetailInfo) {

            val imgUrl = data.googleBook.volumeInfo?.imageLinks?.thumbnail ?: R.drawable.book_no_img

            Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .into(binding.ivBook)

            var quantity = 0

            binding.tvTitle.text = data.googleBook.volumeInfo?.title
            binding.tvPrice.text = context.getString(R.string.order_detail_price_quantity, CommonUtils.makeComma(data.googleBook.saleInfo?.listPrice?.amount ?: 0), data.quantity)
            binding.tvAuthor.text = data.googleBook.volumeInfo?.authors?.joinToString(", ") ?: ""

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailRVAdapter.ViewHolder {
        context = parent.context
        val binding = ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val displayMetrics = context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val itemHeight = (screenHeight * 0.2).toInt()
        binding.root.layoutParams.height = itemHeight
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: OrderDetailRVAdapter.ViewHolder, position: Int) {
        val dto = getItem(position)
        holder.apply {
            bind(dto)
        }

    }

}