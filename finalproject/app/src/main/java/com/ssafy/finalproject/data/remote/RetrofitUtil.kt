package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.base.ApplicationClass

class RetrofitUtil {
    companion object{
        val customerService = ApplicationClass.retrofit.create(CustomerService::class.java)
        val googleBookService = ApplicationClass.retrofit.create(GoogleBookService::class.java)
    }
}