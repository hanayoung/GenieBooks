package com.ssafy.finalproject.data.model.dto

import java.util.ArrayList
import java.util.Date

data class Order(
    val id: Int,
    val userId : Int,
    val orderTime : Date,
    val completed : Boolean,
    val payment : Int,
    val repImgUrl : String?,
    val repBookTitle : String,
    val details : List<OrderDetail>
) {

    constructor(userId: Int, payment: Int, details: List<OrderDetail>) : this(
        id = 0,
        userId = userId,
        orderTime = Date(),
        completed = false,
        payment = payment,
        repImgUrl = null,
        repBookTitle = "",
        details = details
    )
}
