package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentMyPageBinding
import com.ssafy.finalproject.ui.mypage.MyPageViewModel
import com.ssafy.finalproject.util.CommonUtils

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(
    FragmentMyPageBinding::bind,
    R.layout.fragment_my_page
) {

    private val viewModel: MyPageViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        viewModel.myPageInfo.observe(viewLifecycleOwner) {
            binding.tvNickName.text = getString(R.string.my_page_nickname, it.customer.nickname)
            binding.tvPoint.text = CommonUtils.makeCommaWithP(it.customer.point)
            binding.tvWaitingCnt.text = it.inCompleteCnt.toString()
            binding.tvDoneCnt.text = it.completeCnt.toString()
        }
        viewModel.getCustomerInfo()
    }

    private fun initView() {
        binding.iconGift.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_giftCardListFragment)
        }

        binding.tvGift.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_giftCardListFragment)
        }

        binding.btnOrderList.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_orderListFragment)
        }

        binding.tvOrderList.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_orderListFragment)
        }

        binding.tvLogout.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_signOutDialog)
        }

        binding.tvNoti.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_notificationListFragment)
        }

        binding.btnNoti.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_notificationListFragment)
        }

        binding.tvCalendar.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_attendanceFragment)
        }

        binding.tvAiBook.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_recommendFragment)
        }

        binding.btnAttendance.setOnClickListener {
            findNavController().navigate(R.id.action_myPageFragment_to_recommendFragment)
        }
    }
}