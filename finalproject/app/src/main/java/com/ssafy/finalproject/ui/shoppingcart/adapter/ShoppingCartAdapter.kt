package com.ssafy.finalproject.ui.shoppingcart.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.R
import com.ssafy.finalproject.data.model.dto.ShoppingCartBook
import com.ssafy.finalproject.databinding.ItemShoppingCartBinding
import com.ssafy.finalproject.util.CommonUtils
import com.ssafy.finalproject.util.setOnSingleClickListener

private const val TAG = "CategoryDetailAdapter"

class ShoppingCartAdapter :
    ListAdapter<ShoppingCartBook, ShoppingCartAdapter.CustomViewHolder>(CustomComparator) {

    companion object CustomComparator : DiffUtil.ItemCallback<ShoppingCartBook>() {
        override fun areItemsTheSame(
            oldItem: ShoppingCartBook,
            newItem: ShoppingCartBook
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShoppingCartBook,
            newItem: ShoppingCartBook
        ): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener {
        fun onClickDelete(book: ShoppingCartBook)
        fun onClickUpdate()
    }

    inner class CustomViewHolder(private val binding: ItemShoppingCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoppingCartBook) {
            Log.d(TAG, "bind: $item")

            val displayMetrics = binding.root.resources.displayMetrics
            val screenHeight = displayMetrics.heightPixels
            val itemHeight = (screenHeight * 0.2).toInt()
            binding.root.layoutParams.height = itemHeight

            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.book_no_img)
                .into(binding.ivBook)
            binding.tvTitle.text = item.title
            binding.tvPrice.text = CommonUtils.makeComma(item.price)
            val point = CommonUtils.calculatePoints(item.price)
            binding.tvPoint.text =
                binding.root.context.getString(
                    R.string.point_in_shopping_cart,
                    CommonUtils.makeComma(point)
                )
            binding.tvCount.text = item.count.toString()
            binding.ivDelete.setOnSingleClickListener {
                itemClickListener.onClickDelete(item)
            }
            binding.ivMinus.setOnSingleClickListener {
                if (item.count > 1) {
                    binding.tvCount.text = (--item.count).toString()
                    itemClickListener.onClickUpdate()
                }
            }
            binding.ivPlus.setOnSingleClickListener {
                binding.tvCount.text = (++item.count).toString()
                itemClickListener.onClickUpdate()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
            ItemShoppingCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}