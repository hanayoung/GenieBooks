package com.ssafy.finalproject.data.model.dto

import java.util.Date

data class GiftCard(
    val id: Int,
    val title: String,
    val content: String,
    val imgUrl: String,
    val orderTime : Date
)
