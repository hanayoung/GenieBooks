package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.data.model.dto.GiftCardRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface GiftCardService {

    @POST("gift")
    suspend fun insertGiftCard(@Body body: GiftCardRequest): Boolean

    @GET("gift")
    suspend fun getAllGiftCards(@Query("userId") userId: Int): List<GiftCard>

    @PUT("gift")
    suspend fun receiveGiftCard(@Body payload: Map<String, Int>): Int

    @GET("gift/selectById")
    suspend fun getGiftCardById(@Query("giftCardId") giftCardId: Int): GiftCard
}