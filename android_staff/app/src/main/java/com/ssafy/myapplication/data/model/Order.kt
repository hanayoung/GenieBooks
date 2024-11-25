package com.ssafy.finalproject.data.model.dto

import java.util.ArrayList
import java.util.Date

data class Order(
    val id: Int,
    val userId : Int,
    val orderTime : Date,
    val pickupTime : Date,
    var isPickup : Boolean,
    var isDone : Boolean,
    val payment : Int,
    val repImgUrl : String?,
    val repBookTitle : String,
    val details : List<OrderDetail>
)
