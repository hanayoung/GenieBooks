package com.ssafy.finalproject.data.model.dto

import java.util.Date

data class OrderDetail(
    val id: Int,
    val orderId : Int,
    val isbn : Long,
    val quantity : Int
) {
    constructor(isbn : Long, quantity : Int) : this(
        id = 0,
        orderId = 0,
        isbn = isbn,
        quantity = quantity
    )
}
