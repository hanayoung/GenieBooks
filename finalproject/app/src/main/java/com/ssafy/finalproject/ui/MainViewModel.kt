package com.ssafy.finalproject.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.data.model.dto.ShoppingCartBook
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.giftCardService
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel_싸피"

class MainViewModel : ViewModel() {

    private val _isReceiveSuccess: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val isReceiveSuccess: LiveData<Boolean> get() = _isReceiveSuccess

    private var _bookList: MutableLiveData<MutableList<ShoppingCartBook>> =
        MutableLiveData(mutableListOf())
    val bookList: LiveData<MutableList<ShoppingCartBook>> get() = _bookList

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

    fun addShoppingCart(book: ShoppingCartBook) {
        if (_bookList.value?.find { it.id == book.id } == null) {
            _bookList.value?.add(book)
        } else {
            updateBookCount(book.id)
        }
    }

    fun removeShoppingCart(book: ShoppingCartBook) {
        _bookList.value?.remove(book)
        changeBookList()
    }

    private fun updateBookCount(id: String) {
        _bookList.value?.find { it.id == id }?.let { book ->
            val updatedBook = book.copy(count = book.count + 1)
            _bookList.value!![_bookList.value!!.indexOf(book)] = updatedBook
        }
    }

    private fun changeBookList() {
        _bookList.value = _bookList.value?.toMutableList()
    }

    fun clearShoppingCart() {
        _bookList.value?.clear()
    }

}