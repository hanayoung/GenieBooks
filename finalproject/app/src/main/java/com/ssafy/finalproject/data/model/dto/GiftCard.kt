package com.ssafy.finalproject.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class GiftCard(
    val id: Int,
    val title: String,
    val content: String,
    val imgUrl: String,
    val giftDate : Date,
    val recipientId : Int,
    val senderId : Int,
    val senderName : String
) : Parcelable
