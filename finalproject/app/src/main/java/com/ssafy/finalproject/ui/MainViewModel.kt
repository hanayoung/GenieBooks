package com.ssafy.finalproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.finalproject.data.model.dto.ShoppingCartBook

class MainViewModel: ViewModel() {

    private var _bookList: MutableLiveData<MutableList<ShoppingCartBook>> = MutableLiveData(mutableListOf())
    val bookList: LiveData<MutableList<ShoppingCartBook>> get() = _bookList

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
}