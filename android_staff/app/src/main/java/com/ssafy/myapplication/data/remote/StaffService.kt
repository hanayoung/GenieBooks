package com.ssafy.myapplication.data.remote

import com.ssafy.myapplication.data.model.Staff
import retrofit2.http.Body
import retrofit2.http.POST

interface StaffService {

    @POST("staff/signup")
    suspend fun join(@Body body:Staff): Boolean

}