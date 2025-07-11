package com.ssafy.finalproject.data.model.dto

import java.util.Date

data class OrderInfo(
    val id: Int,
    val userId : Int,
    val orderTime : Date,
    val pickupTime : Date,
    var pickup : Boolean,
    var done : Boolean,
    val payment : Int,
    val repImgUrl : String?,
    val repBookTitle : String,
    val details : List<OrderDetailInfo>
)
