package com.ssafy.finalproject.data.remote

import com.ssafy.finalproject.base.ApplicationClass

class RetrofitUtil {
    companion object{
        val customerService: CustomerService = ApplicationClass.retrofit.create(CustomerService::class.java)
        val googleBookService: GoogleBookService = ApplicationClass.retrofit.create(GoogleBookService::class.java)
    }
}