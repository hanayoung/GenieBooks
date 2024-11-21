package com.ssafy.finalproject.ui.joincategory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.Customer
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.customerService
import kotlinx.coroutines.launch

private const val TAG = "JoinCategoryViewModel_싸피"
class JoinCategoryViewModel: ViewModel() {

    private var _isJoinSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isJoinSuccess : LiveData<Boolean> get() = _isJoinSuccess

    fun join(customer: Customer) {
        viewModelScope.launch {
            runCatching {
                customerService.join(customer)
            }.onSuccess {
                _isJoinSuccess.value = true
            }.onFailure {
                Log.d(TAG, "join: ${it.message}")
            }
        }
    }
}