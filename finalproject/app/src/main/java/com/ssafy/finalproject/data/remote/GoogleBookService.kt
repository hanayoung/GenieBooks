package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.GoogleBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBookService {
    @GET("book/recommend")
    suspend fun getAllRecommendBooks(): Response<List<GoogleBook>>

    @GET("book/search")
    suspend fun getAllCategoryBooks(
        @Query("category") category: String
    ): Response<List<GoogleBook>>
}