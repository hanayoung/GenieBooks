package com.ssafy.myapplication.ui.orderdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.myapplication.data.model.OrderInfo
import com.ssafy.myapplication.data.remote.RetrofitUtil
import kotlinx.coroutines.launch
import okhttp3.RequestBody

private const val TAG = "OrderDetailViewModel"
class OrderDetailViewModel: ViewModel() {
    private var _orderDetail : MutableLiveData<OrderInfo> = MutableLiveData()
    val orderDetail : LiveData<OrderInfo> get() = _orderDetail

    fun getOrderDetail(orderId: Int) {
        viewModelScope.launch {
            runCatching {
                RetrofitUtil.orderService.getOrderDetail(orderId)
            }.onSuccess {
                Log.d(TAG, "getOrderDetail: ${it}")
                _orderDetail.value = it
            }.onFailure {
                Log.d(TAG, "getOrderDetail: fail ${it.message}")
            }
        }
    }

    fun updateOrderStatePickup(requestBody: RequestBody) {
        viewModelScope.launch {
            runCatching {
                RetrofitUtil.staffService.updateOrderStatePickup(requestBody)
            }.onSuccess {
                Log.d(TAG, "updateOrderStatePickup: ${it}")
            }.onFailure {
                Log.d(TAG, "updateOrderStatePickup fail: ${it.message}")
            }
        }
    }

    fun updateOrderStateDone(requestBody: RequestBody) {
        viewModelScope.launch {
            runCatching {
                RetrofitUtil.staffService.updateOrderStateDone(requestBody)
            }.onSuccess {
                Log.d(TAG, "updateOrderStateDone: ${it}")
            }.onFailure {
                Log.d(TAG, "updateOrderStateDone: ${it.message}")
            }
        }
    }

    fun setIsDone() {
        _orderDetail.value?.done = true
    }

}