package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.Customer
import com.ssafy.finalproject.data.model.dto.LoginInfoResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CustomerService {
    @POST("customer/signup")
    suspend fun join(@Body body: Customer): Boolean

    @POST("customer/login")
    suspend fun login(@Body body: RequestBody): Customer

    @GET("customer/info")
    suspend fun getInfo(@Query("id") id: String): LoginInfoResponse

    @GET("customer/isUsed")
    suspend fun isUsedId(@Query("id") id: String): Boolean
}