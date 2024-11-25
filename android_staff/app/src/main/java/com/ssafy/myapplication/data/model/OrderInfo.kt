package com.ssafy.myapplication.data.model

import com.ssafy.finalproject.data.model.dto.OrderDetailInfo
import java.util.Date

data class OrderInfo(
    val id: Int,
    val userId : Int,
    val orderTime : Date,
    val pickupTime : Date,
    var isPickup : Boolean,
    var isDone : Boolean,
    val payment : Int,
    val repImgUrl : String?,
    val repBookTitle : String,
    val details : List<OrderDetailInfo>
)
