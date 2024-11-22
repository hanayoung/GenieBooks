package com.ssafy.finalproject.data.model.dto

data class OrderInfo(
    val id: Int,
    val userId : Int,
    val orderTime : Long,
    val completed : Boolean,
    val payment : Int,
    val repImgUrl : String?,
    val detail : List<OrderDetailInfo>
)
