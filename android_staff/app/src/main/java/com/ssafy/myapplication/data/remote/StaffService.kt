package com.ssafy.myapplication.data.remote

import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.myapplication.data.model.Staff
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface StaffService {

    @POST("staff/signup")
    suspend fun join(@Body body:Staff): Boolean

    @POST("staff/login")
    suspend fun login(@Body body: Staff): Staff?

    @GET("staff/isUsed")
    suspend fun isUsedId(@Query("id") id: String): Boolean

    @GET("staff/order")
    suspend fun getAllOrders(): List<Order>

    @PUT("staff/order/complete")
    suspend fun updateOrderState(@Body body: RequestBody) : Boolean
}