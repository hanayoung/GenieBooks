package com.ssafy.finalproject.ui.attendance

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.remote.RetrofitUtil
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.math.log

private const val TAG = "AttendanceViewModel"
class AttendanceViewModel: ViewModel() {
    private var _attendanceList =  MutableLiveData<MutableList<Date>>()
    val attendanceList : LiveData<MutableList<Date>>
        get() = _attendanceList

    private var _isAttend = MutableLiveData<Boolean>()
    val isAttend : LiveData<Boolean>
        get() = _isAttend

init {
    viewModelScope.launch {
        runCatching {
            val id = ApplicationClass.sharedPreferencesUtil.getId()
            RetrofitUtil.attendanceService.getAttendances(id)
        }.onSuccess {
            Log.d(TAG, "Attendance : ${it}")
            _attendanceList.value = it as MutableList<Date>
        }.onFailure {
            Log.d(TAG, "Attendance fail : ${it.message}")
        }
    }
}
    fun addAttendance() {
        viewModelScope.launch { 
            runCatching {
                val id = ApplicationClass.sharedPreferencesUtil.getId()
                RetrofitUtil.attendanceService.addAttendance(id)
            }.onSuccess {
                Log.d(TAG, "addAttendance: ${it}")
                _isAttend.value = it
            }.onFailure {
                Log.d(TAG, "addAttendance: fail ${it.message}")
            }
        }
    } 

}