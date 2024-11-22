package com.ssafy.finalproject.ui.mypage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.R
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.finalproject.data.model.dto.OrderDetail
import com.ssafy.finalproject.databinding.ItemBookListBinding
import com.ssafy.finalproject.util.CommonUtils
import java.util.Date

class WaitingRVAdapter: ListAdapter<Order, WaitingRVAdapter.ViewHolder>(IdComparator) {
    lateinit var itemClickListener: ItemClickListener
    private lateinit var context: Context

    interface ItemClickListener {
        fun onClick(view: View, data: Order, position: Int)
    }

    companion object IdComparator : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemBookListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Order) {

            val imgUrl = data.repImgUrl ?: R.drawable.book_no_img

            Glide.with(context)
                .load(imgUrl)
                .centerCrop()
                .into(binding.ivBook)

            var quantity = 0
            for(i in 0 until data.detail.size){
                quantity += data.detail[i].quantity
            }

            binding.tvPrice.text = context.getString(R.string.order_list_content, data.detail.size, quantity, CommonUtils.makeComma(data.payment))
            binding.tvTitle.text = CommonUtils.dateformatYMD(Date(data.orderTime))
            if(data.detail.size > 1){
                binding.tvAuthor.text = context.getString(R.string.order_list_title, data.repTitle, data.detail.size-1)
            } else {
                binding.tvAuthor.text = data.repTitle
            }

            binding.book.setOnClickListener {
                itemClickListener.onClick(it, data, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WaitingRVAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: WaitingRVAdapter.ViewHolder, position: Int) {
        val dto = getItem(position)
        holder.apply {
            bind(dto)
        }

    }
}