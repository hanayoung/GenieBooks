package com.ssafy.finalproject.data.model.dto

data class GiftCardRequest(
    val title: String,
    val content: String,
    val imgUrl: String,
    val senderId : Int,
    val senderName : String
)
