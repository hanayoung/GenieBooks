package com.ssafy.finalproject.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.remote.RetrofitUtil
import com.ssafy.finalproject.ui.home.model.GoogleBook
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"
class HomeViewModel : ViewModel() {
    private val _bookList = MutableLiveData<List<GoogleBook>>()
    val bookList : LiveData<List<GoogleBook>>
        get() = _bookList


    init {
        viewModelScope.launch {
            runCatching {
                RetrofitUtil.googleBookService.getAllRecommendBooks()
            }.onSuccess {  res ->
                if(res.isSuccessful){
                    _bookList.value = res.body()
                    Log.d(TAG, "_bookList: $bookList")
                }else {
                    _bookList.value = emptyList()
                    Log.d(TAG, "_bookList fail: ${res.errorBody()?.string().toString()}")
                }
            }.onFailure {
                Log.d(TAG, "fail : ${it.message}")
            }
        }
    }


}