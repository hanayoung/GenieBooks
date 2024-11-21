package com.ssafy.myapplication.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.myapplication.data.model.Staff
import com.ssafy.myapplication.data.remote.RetrofitUtil.Companion.staffService
import kotlinx.coroutines.launch

private const val TAG = "LoginViewModel_싸피"
class LoginViewModel: ViewModel() {

    private var _isLoginSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isLoginSuccess : LiveData<Boolean> get() = _isLoginSuccess

    private var _loginUser: MutableLiveData<Staff?> = MutableLiveData()
    val loginUser get() = _loginUser

    fun login(staff: Staff) {
        viewModelScope.launch {
            runCatching {
                staffService.login(staff)
            }.onSuccess {
                _loginUser.value = it
                _isLoginSuccess.value = true
                Log.d(TAG, "login: $it")
            }.onFailure {
                _isLoginSuccess.value = false
                Log.d(TAG, "login: ${it.message}")
            }
        }
    }
}