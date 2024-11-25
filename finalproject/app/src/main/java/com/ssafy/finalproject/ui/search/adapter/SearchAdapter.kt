package com.ssafy.finalproject.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.R
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.databinding.ItemBookListBinding
import com.ssafy.finalproject.util.CommonUtils

private const val TAG = "SearchAdapter"

class SearchAdapter(private val itemClickListener: ItemClickListener) :
    ListAdapter<GoogleBook, SearchAdapter.CustomViewHolder>(CustomComparator) {

    companion object CustomComparator : DiffUtil.ItemCallback<GoogleBook>() {
        override fun areItemsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
            return oldItem == newItem
        }
    }

    fun interface ItemClickListener {
        fun onClick(id: String)
    }

    inner class CustomViewHolder(private val binding: ItemBookListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GoogleBook) {
            Glide.with(binding.root.context)
                .load(item.volumeInfo?.imageLinks?.thumbnail)
                .placeholder(R.drawable.book_no_img)
                .into(binding.ivBook)
            binding.tvTitle.text = item.volumeInfo?.title
            binding.tvAuthor.text = item.volumeInfo?.authors?.get(0) ?: ""
            binding.tvPrice.text = CommonUtils.makeComma(item.saleInfo?.listPrice?.amount ?: 0)
            binding.root.setOnClickListener {
                itemClickListener.onClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
            ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val displayMetrics = parent.context.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val itemHeight = (screenHeight * 0.2).toInt()
        binding.root.layoutParams.height = itemHeight
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}