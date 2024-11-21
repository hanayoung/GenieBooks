package com.ssafy.myapplication.ui.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.myapplication.data.model.Staff
import com.ssafy.myapplication.data.remote.RetrofitUtil.Companion.staffService
import kotlinx.coroutines.launch

private const val TAG = "JoinViewModel_싸피"
class JoinViewModel: ViewModel() {

    private var _isJoinSuccess : MutableLiveData<Boolean> = MutableLiveData()
    val isJoinSuccess : LiveData<Boolean> get() = _isJoinSuccess

    fun join(staff: Staff) {
        viewModelScope.launch {
            runCatching {
                staffService.join(staff)
            }.onSuccess {
                Log.d(TAG, "join: $it")
                _isJoinSuccess.value = it
            }.onFailure {
                Log.d(TAG, "join: ${it.message}")
            }
        }
    }
}