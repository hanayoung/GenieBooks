package com.ssafy.finalproject.ui.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.finalproject.ui.mypage.fragment.PickUpCompleteFragment
import com.ssafy.finalproject.ui.mypage.fragment.PickUpWaitingFragment

class OrderListVPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf<Fragment>(
        PickUpWaitingFragment(),
        PickUpCompleteFragment()
    )
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
    fun getFragmentAtPosition(position: Int): Fragment {
        // 특정 위치의 프래그먼트를 가져오는 메서드
        return createFragment(position)
    }
}