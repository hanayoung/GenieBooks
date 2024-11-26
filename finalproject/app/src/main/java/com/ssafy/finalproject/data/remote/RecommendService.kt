package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.GoogleBook
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendService {

    @GET("recommend")
    suspend fun getRecommendBooks(@Query("userId") userId: Int): List<GoogleBook>
}