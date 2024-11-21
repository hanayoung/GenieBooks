package com.ssafy.finalproject.ui.categorydetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.R
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.databinding.ItemCategoryDetailBinding

class CategoryDetailAdapter :
    ListAdapter<GoogleBook, CategoryDetailAdapter.CustomViewHolder>(CustomComparator) {

    companion object CustomComparator : DiffUtil.ItemCallback<GoogleBook>() {
        override fun areItemsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
            return oldItem == newItem
        }
    }

    inner class CustomViewHolder(private val binding: ItemCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GoogleBook) {
            Glide.with(binding.root.context)
                .load(item.volumeInfo?.imageLinks?.thumbnail)
                .placeholder(R.drawable.book_no_img)
                .into(binding.ivBook)
            binding.tvTitle.text = item.volumeInfo?.title
            binding.tvAuthor.text = item.volumeInfo?.authors?.get(0) ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
            ItemCategoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}