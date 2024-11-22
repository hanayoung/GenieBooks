package com.ssafy.finalproject.data.model.dto

data class Order(
    val id: Int,
    val userId : Int,
    val orderTime : Long,
    val completed : Boolean,
    val payment : Int,
    val repImgUrl : String?,
    val repTitle : String,
    val detail : List<OrderDetail>
)
