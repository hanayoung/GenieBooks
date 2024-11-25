package com.ssafy.finalproject.ui.gift

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.data.model.dto.GiftCardRequest
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.giftCardService
import com.ssafy.finalproject.ui.Event
import kotlinx.coroutines.launch

private const val TAG = "MakeGiftCardViewModel_싸피"
class MakeGiftCardViewModel: ViewModel(){

    private val _imageUri: MutableLiveData<Uri> = MutableLiveData()
    private val imageUri: LiveData<Uri> get() = _imageUri

    private val _imagePathEvent: MutableLiveData<Event<String>> = MutableLiveData()
    val imagePathEvent: LiveData<Event<String>> get() = _imagePathEvent

    private val _isSendSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val isSendSuccess: LiveData<Boolean> get() = _isSendSuccess

    fun selectImage(uri: Uri) {
        _imageUri.value = uri
    }

    fun uploadImage(userId: Int, timeStamp: Long) {
        val storageRef = Firebase.storage.reference
            .child("$userId/$timeStamp")

        Log.d(TAG, "uploadImage: ${imageUri.value}")

        imageUri.value?.let {
            storageRef.putFile(it).addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    _imagePathEvent.value = Event(it.toString())
                    Log.d(TAG, "uploadImage: $it")
                }?.addOnFailureListener {
                    Log.d(TAG, "uploadImage fail: ${it.message}")
                }
            }.addOnFailureListener {
                Log.d(TAG, "uploadImage error: ${it.message}")
            }
        }
    }

    fun insertGiftCard(giftCard : GiftCardRequest) {
        Log.d(TAG, "insertGiftCard: ")
        viewModelScope.launch {
            runCatching {
                giftCardService.insertGiftCard(giftCard)
            }.onSuccess {
                _isSendSuccess.value = it
                Log.d(TAG, "insertGiftCard: $it")
            }.onFailure {
                Log.d(TAG, "insertGiftCard: ${it.message}")
            }
        }
    }
}