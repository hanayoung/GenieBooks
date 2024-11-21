package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.Customer
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerService {
    @POST("customer/signup")
    suspend fun join(@Body body: Customer): Boolean

    @POST("customer/login")
    suspend fun login(@Body body: RequestBody): Customer
}