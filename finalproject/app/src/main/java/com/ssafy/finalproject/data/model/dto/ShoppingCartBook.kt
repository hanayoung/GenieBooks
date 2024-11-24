package com.ssafy.finalproject.data.model.dto

data class ShoppingCartBook(
    val id: String,
    val imageUrl: String,
    val title: String,
    val price: Int,
    var count: Int
)
