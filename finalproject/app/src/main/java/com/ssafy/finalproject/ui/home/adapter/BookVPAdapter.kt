package com.ssafy.finalproject.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssafy.finalproject.databinding.ItemVpBookBinding
import com.ssafy.finalproject.ui.home.model.GoogleBook

class BookVPAdapter: ListAdapter<GoogleBook, BookVPAdapter.ViewHolder>(IdComparator) {
    lateinit var itemClickListener: ItemClickListener
    private lateinit var context: Context
    interface ItemClickListener{
        fun onClick(view: View, data: GoogleBook, position: Int)
    }

    companion object IdComparator: DiffUtil.ItemCallback<GoogleBook>(){
        override fun areItemsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GoogleBook, newItem: GoogleBook): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(val binding: ItemVpBookBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data:GoogleBook){
            data.volumeInfo?.imageLinks?.thumbnail?.let {
                Glide.with(context)
                    .load(it)
                    .centerCrop()
                    .into(binding.img)
            }

            binding.book.setOnClickListener {
                itemClickListener.onClick(it, data, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemVpBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dto = getItem(position)
        holder.apply {
            bind(dto)
        }

    }

}