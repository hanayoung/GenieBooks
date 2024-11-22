package com.ssafy.finalproject.data.model.dto

data class LoginInfoResponse(
    val inCompleteCnt : Int,
    val completeCnt : Int,
    val customer : Customer
)
