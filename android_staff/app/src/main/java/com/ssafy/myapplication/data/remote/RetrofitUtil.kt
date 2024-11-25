package com.ssafy.myapplication.data.remote

import com.ssafy.myapplication.base.ApplicationClass

class RetrofitUtil {
    companion object{
        val staffService: StaffService = ApplicationClass.retrofit.create(StaffService::class.java)
        val orderService: OrderService = ApplicationClass.retrofit.create(OrderService::class.java)
    }
}