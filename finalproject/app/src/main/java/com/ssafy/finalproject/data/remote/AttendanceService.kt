package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.Customer
import com.ssafy.finalproject.data.model.dto.LoginInfoResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Date

interface AttendanceService {
    @GET("attendance")
    suspend fun getAttendances(@Query("id") id: String): List<Date>

    @GET("attendance/add")
    suspend fun addAttendance(@Query("id") id: String): Boolean
}