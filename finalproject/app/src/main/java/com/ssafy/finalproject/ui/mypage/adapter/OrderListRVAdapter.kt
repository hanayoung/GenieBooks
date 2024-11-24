package com.ssafy.finalproject.ui.mypage.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.R
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.finalproject.databinding.ItemBookListBinding
import com.ssafy.finalproject.util.CommonUtils

private const val TAG = "WaitingRVAdapter"
class OrderListRVAdapter: ListAdapter<Order, OrderListRVAdapter.ViewHolder>(IdComparator) {
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
            for(i in 0 until data.details.size){
                quantity += data.details[i].quantity
            }
            Log.d(TAG, "bind: ${data.orderTime}  ${data.orderTime::class.simpleName}")
            binding.tvPrice.text = context.getString(R.string.order_list_content, data.details.size, quantity, CommonUtils.makeComma(data.payment))
            binding.tvTitle.text = CommonUtils.dateformatYMD(data.orderTime)
            if(data.details.size > 1){
                binding.tvAuthor.text = context.getString(R.string.order_list_title, data.repBookTitle, data.details.size-1)
            } else {
                binding.tvAuthor.text = data.repBookTitle
            }

            binding.book.setOnClickListener {
                itemClickListener.onClick(it, data, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderListRVAdapter.ViewHolder {
        context = parent.context
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val displayMetrics = context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val itemHeight = (screenHeight * 0.2).toInt()
        binding.root.layoutParams.height = itemHeight
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: OrderListRVAdapter.ViewHolder, position: Int) {
        val dto = getItem(position)
        holder.apply {
            bind(dto)
        }

    }
}