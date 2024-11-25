package com.ssafy.finalproject.data.model.dto

import com.ssafy.myapplication.data.model.GoogleBook

data class OrderDetailInfo(
    val id: Int,
    val orderId : Int,
    val isbn : Long,
    val quantity : Int,
    val googleBook: GoogleBook,
    val sumPrice : Int
)
