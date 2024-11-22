package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.Order
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderService {
    @GET("order")
    suspend fun getOrderList(@Query("id") id: String) : List<Order>
}