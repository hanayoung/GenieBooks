package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.data.model.dto.GiftCardRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GiftCardService {

    @POST("gift")
    suspend fun insertGiftCard(@Body body: GiftCardRequest): Boolean
}