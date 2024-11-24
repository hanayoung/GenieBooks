package com.ssafy.finalproject.ui.bookdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.googleBookService
import kotlinx.coroutines.launch

private const val TAG = "BookDetailViewModel_싸피"

class BookDetailViewModel : ViewModel() {
    private val _book = MutableLiveData<GoogleBook>()
    val book: LiveData<GoogleBook>
        get() = _book

    fun getBookById(id: String) {
        viewModelScope.launch {
            runCatching {
                googleBookService.getBookById(id)
            }.onSuccess { res ->
                if (res.isSuccessful) {
                    _book.value = res.body()
                    Log.d(TAG, "getBookById: ${res.body()}")
                } else {
                    Log.d(TAG, "getBookById fail: ${res.errorBody()?.string().toString()}")
                    Log.d(TAG, "getBookById fail: ${res.code()}")
                }
            }.onFailure {
                Log.d(TAG, "fail : ${it.message}")
            }
        }
    }
}