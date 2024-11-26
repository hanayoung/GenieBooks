package com.ssafy.finalproject.ui.recommend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.recommendService
import kotlinx.coroutines.launch

private const val TAG = "RecommendViewModel"
class RecommendViewModel: ViewModel() {

    private val userId = ApplicationClass.sharedPreferencesUtil.getUserId()
    private val _recommendBookList: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val recommendBookList: LiveData<List<GoogleBook>> get() = _recommendBookList

    init {
        getRecommendBooks(userId)
    }
    
    private fun getRecommendBooks(userId: Int) {
        viewModelScope.launch { 
            runCatching { 
                recommendService.getRecommendBooks(userId)
            }.onSuccess { 
                _recommendBookList.value = it
                Log.d(TAG, "getRecommendBooks: $it")
            }.onFailure {
                Log.d(TAG, "getRecommendBooks: ${it.message}")
            }
        }
    }
}