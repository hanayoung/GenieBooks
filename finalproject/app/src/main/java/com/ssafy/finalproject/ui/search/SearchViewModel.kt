package com.ssafy.finalproject.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.googleBookService
import kotlinx.coroutines.launch

private const val TAG = "SearchViewModel_싸피"

class SearchViewModel : ViewModel() {

    private val _bookList: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val bookList: LiveData<List<GoogleBook>> get() = _bookList

    fun getBooksByKeyword(keyword: String) {
        viewModelScope.launch {
            runCatching {
                googleBookService.getBooksByKeyword(keyword)
            }.onSuccess { res ->
                if (res.isSuccessful) {
                    _bookList.value = res.body()
                    Log.d(TAG, "getBooksByKeyword: ${bookList.value}")
                } else {
                    _bookList.value = emptyList()
                    Log.d(TAG, "getBooksByKeyword fail: ${res.code()}")
                }
            }.onFailure {
                Log.d(TAG, "getBooksByKeyword: ${it.message}")
            }
        }
    }
}