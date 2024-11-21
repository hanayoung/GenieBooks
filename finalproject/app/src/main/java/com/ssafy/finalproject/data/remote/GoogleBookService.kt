package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.GoogleBook
import retrofit2.Response
import retrofit2.http.GET

interface GoogleBookService {
    @GET("book/recommend")
    suspend fun getAllRecommendBooks(): Response<List<GoogleBook>>
}