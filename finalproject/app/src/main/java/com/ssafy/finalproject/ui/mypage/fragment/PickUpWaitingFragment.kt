package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentPickUpCompleteBinding
import com.ssafy.finalproject.databinding.FragmentPickUpWaitingBinding
import com.ssafy.finalproject.ui.mypage.OrderListViewModel
import com.ssafy.finalproject.ui.mypage.adapter.WaitingRVAdapter

class PickUpWaitingFragment :  BaseFragment<FragmentPickUpWaitingBinding>(
    FragmentPickUpWaitingBinding::bind,
    R.layout.fragment_pick_up_waiting
) {

    private lateinit var waitingRVAdapter: WaitingRVAdapter
    private val viewModel : OrderListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        waitingRVAdapter = WaitingRVAdapter()

        binding.bookRv.apply {
            adapter = waitingRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.orderList.observe(viewLifecycleOwner){
            waitingRVAdapter.submitList(it)
        }
    }
}