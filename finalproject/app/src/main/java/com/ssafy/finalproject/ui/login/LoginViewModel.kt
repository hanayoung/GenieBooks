package com.ssafy.finalproject.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.giftCardService
import kotlinx.coroutines.launch

private const val TAG = "LoginViewModel_싸피"
class LoginViewModel: ViewModel() {

    private val _isReceiveSuccess: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isReceiveSuccess: LiveData<Boolean> get() = _isReceiveSuccess

    fun receiveGiftCard(userId: Int, giftCardId: Int) {
        viewModelScope.launch {
            runCatching {
                val map = mapOf("recipientId" to userId, "giftCardId" to giftCardId)
                giftCardService.receiveGiftCard(map)
            }.onSuccess {
                if (it >= 1) {
                    _isReceiveSuccess.value = true
                }
                Log.d(TAG, "receiveGiftCard: $it")
            }.onFailure {
                Log.d(TAG, "receiveGiftCard: ${it.message}")
            }
        }
    }
}