package com.ssafy.finalproject.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.OrderInfo
import com.ssafy.finalproject.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

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

}