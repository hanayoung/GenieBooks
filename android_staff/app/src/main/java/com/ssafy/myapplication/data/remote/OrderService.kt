package com.ssafy.myapplication.data.remote

import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.myapplication.data.model.OrderInfo
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderService {
    @GET("order/{orderId}")
    suspend fun getOrderDetail(@Path(value = "orderId") orderId: Int) : OrderInfo

}