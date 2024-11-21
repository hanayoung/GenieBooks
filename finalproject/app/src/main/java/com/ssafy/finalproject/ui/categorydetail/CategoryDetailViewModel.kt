package com.ssafy.finalproject.ui.categorydetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.googleBookService
import kotlinx.coroutines.launch

private const val TAG = "CategoryDetailViewModel_싸피"

class CategoryDetailViewModel : ViewModel() {
    private val _bookList = MutableLiveData<List<GoogleBook>>()
    val bookList : LiveData<List<GoogleBook>>
        get() = _bookList

    fun getAllCategoryBooks(category: String) {
        viewModelScope.launch {
            runCatching {
                googleBookService.getAllCategoryBooks(category)
            }.onSuccess {  res ->
                if(res.isSuccessful){
                    _bookList.value = res.body()
                    Log.d(TAG, "_bookList: ${bookList.value}")
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