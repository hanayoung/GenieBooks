package com.ssafy.finalproject.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.LoginInfoResponse
import com.ssafy.finalproject.data.remote.RetrofitUtil
import com.ssafy.finalproject.util.CommonUtils
import kotlinx.coroutines.launch

private const val TAG = "MyPageViewModel"
class MyPageViewModel: ViewModel() {
    private var _myPageInfo : MutableLiveData<LoginInfoResponse> = MutableLiveData()
    val myPageInfo : LiveData<LoginInfoResponse> get() = _myPageInfo
    init {
        viewModelScope.launch {
            runCatching {
                val id = ApplicationClass.sharedPreferencesUtil.getId()
                RetrofitUtil.customerService.getInfo(id)
            }.onSuccess {
                _myPageInfo.value = it
            }.onFailure {
                Log.d(TAG, "fail : ${it.message}")
            }
        }
    }
}