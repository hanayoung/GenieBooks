package com.ssafy.finalproject.ui.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.customerService
import com.ssafy.finalproject.ui.Event
import kotlinx.coroutines.launch

private const val TAG = "JoinViewModel"
class JoinViewModel: ViewModel() {

    private var _isUsedId: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isUsedId: LiveData<Event<Boolean>> get() = _isUsedId

    fun getIsUsedId(id: String) {
        viewModelScope.launch {
            runCatching {
                customerService.isUsedId(id)
            }.onSuccess {
                _isUsedId.value = Event(it)
            }.onFailure {
                Log.d(TAG, "getIsUsedId: ${it.message}")
            }
        }
    }
}