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
import com.ssafy.finalproject.databinding.ItemVpBookBinding
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.model.dto.Notification
import com.ssafy.finalproject.databinding.ItemNotificationBinding
import com.ssafy.finalproject.util.CommonUtils
import java.util.Date

class NotificationRVAdapter: ListAdapter<String, NotificationRVAdapter.ViewHolder>(IdComparator) {
    lateinit var itemClickListener: ItemClickListener
    private lateinit var context: Context
    interface ItemClickListener{
        fun onClick(view: View, data: String, position: Int)
    }

    companion object IdComparator: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemNotificationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String){
            binding.btnRemove.setOnClickListener {
                itemClickListener.onClick(it, data, adapterPosition) // TODO FCM 연결 후 해당 알림 지우기
            }

//            binding.tvDate.text = CommonUtils.dateformatYMDHM(Date(data.date))
            binding.tvContent.text = data
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context = parent.context
        return ViewHolder(
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dto = getItem(position)
        holder.apply {
            bind(dto)
        }

    }

}