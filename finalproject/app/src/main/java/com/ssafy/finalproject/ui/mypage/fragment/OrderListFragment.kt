package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentMyPageBinding
import com.ssafy.finalproject.databinding.FragmentOrderListBinding
import com.ssafy.finalproject.ui.MainActivity
import com.ssafy.finalproject.ui.mypage.adapter.NotificationRVAdapter
import com.ssafy.finalproject.ui.mypage.adapter.OrderListVPAdapter

class OrderListFragment : BaseFragment<FragmentOrderListBinding>(
    FragmentOrderListBinding::bind,
    R.layout.fragment_order_list
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("픽업 대기"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("픽업 완료"))

        binding.listRv.adapter = OrderListVPAdapter(requireActivity() as MainActivity)

        TabLayoutMediator(binding.tabLayout, binding.listRv){ tab, position ->
            tab.text = if (position == 0) "픽업 대기" else "픽업 완료"
        }.attach()
    }
}