package com.ssafy.finalproject.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.Notification

private const val TAG = "NotificationViewModel"
class NotificationViewModel : ViewModel() {
    private var _notiList = MutableLiveData<List<String>>()
    val notiList : LiveData<List<String>>
        get() = _notiList

    init {
        _notiList.value = ApplicationClass.sharedPreferencesUtil.getNotice()
        Log.d(TAG, "notification: ${_notiList.value}")
    }
}