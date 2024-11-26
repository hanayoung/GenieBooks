package com.ssafy.myapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.ssafy.myapplication.data.remote.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

private const val TAG = "QRScanViewModel"
class QRScanViewModel: ViewModel() {
    private var _orderId : MutableLiveData<Int> = MutableLiveData<Int>()
    val orderId : LiveData<Int>
        get() = _orderId

    fun updatePickupState(orderId: Int){
        viewModelScope.launch {
            runCatching {
                val userId = getOrderUserId(orderId)
                val jsonObject = JsonObject().apply {
                    addProperty("userId", userId)
                    addProperty("orderId", orderId)
                }
                val requestBody = RequestBody.create(
                    "application/json".toMediaTypeOrNull(),
                    jsonObject.toString()
                )
                RetrofitUtil.staffService.updateOrderStatePickup(requestBody)
            }.onSuccess {
                Log.d(TAG, "updateOrderStateDone: ${it}")
            }.onFailure {
                Log.d(TAG, "updateOrderStateDone: ${it.message}")
            }
        }
    }

    suspend fun getOrderUserId(orderId: Int): Int = withContext(Dispatchers.IO) {
        try {
            val response = RetrofitUtil.staffService.getUserIdByOrderId(orderId)
            Log.d(TAG, "getOrderDetail: $response")
            response
        } catch (e: Exception) {
            Log.d(TAG, "getOrderDetail: fail ${e.message}")
            -1 // 오류 시 기본값 반환
        }
    }
}