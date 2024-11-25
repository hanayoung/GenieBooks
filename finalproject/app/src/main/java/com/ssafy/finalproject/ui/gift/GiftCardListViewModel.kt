package com.ssafy.finalproject.ui.gift

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.data.remote.RetrofitUtil
import kotlinx.coroutines.launch

private const val TAG = "GiftCardListViewModel"
class GiftCardListViewModel: ViewModel() {
    private val _giftCardList: MutableLiveData<List<GiftCard>> = MutableLiveData()
    val giftCardList: LiveData<List<GiftCard>> get() = _giftCardList

    init {
        viewModelScope.launch {
            runCatching {
                val userId = ApplicationClass.sharedPreferencesUtil.getUserId()
                RetrofitUtil.giftCardService.getAllGiftCards(userId)
            }.onSuccess {
                Log.d(TAG, "success : ${it}")
                _giftCardList.value = it
            }.onFailure {
                Log.d(TAG, "fail : ${it.message}")
            }
        }
    }
}