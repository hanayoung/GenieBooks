package com.ssafy.finalproject.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.LoginInfoResponse
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.finalproject.data.remote.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.log

private const val TAG = "OrderListViewModel"
class OrderListViewModel: ViewModel() {
    private var _orderList : MutableLiveData<List<Order>> = MutableLiveData()
    val orderList : LiveData<List<Order>> get() = _orderList

    init {
        viewModelScope.launch {
            runCatching {
                val id = ApplicationClass.sharedPreferencesUtil.getId()
                RetrofitUtil.orderService.getOrderList(id)
            }.onSuccess {
                Log.d(TAG, "success : ${it}")
                _orderList.value = it
            }.onFailure {
                Log.d(TAG, "fail: ${it.message}")
            }
        }
    }
}