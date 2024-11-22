package com.ssafy.finalproject.data.model.dto

data class OrderDetailInfo(
    val id: Int,
    val orderId : Int,
    val isbn : Long,
    val quantity : Int,
    val googleBook: GoogleBook,
    val sumPrice : Int
)
