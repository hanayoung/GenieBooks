package com.ssafy.myapplication.ui.orderlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.myapplication.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "OrderListViewModel"
class OrderListViewModel: ViewModel() {
    private var _orderList : MutableLiveData<List<Order>> = MutableLiveData()
    val orderList : LiveData<List<Order>>
        get() = _orderList

    init {
        viewModelScope.launch {
            runCatching {
                RetrofitUtil.staffService.getAllOrders()
            }.onSuccess {
                Log.d(TAG, "success: ${it}")
                _orderList.value = it
            }.onFailure {
                Log.d(TAG, "fail : ${it.message}")
            }
        }
    }

}