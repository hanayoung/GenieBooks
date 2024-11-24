package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.finalproject.databinding.FragmentPickUpWaitingBinding
import com.ssafy.finalproject.ui.mypage.OrderListViewModel
import com.ssafy.finalproject.ui.mypage.adapter.OrderListRVAdapter

private const val TAG = "PickUpWaitingFragment"
class PickUpWaitingFragment :  BaseFragment<FragmentPickUpWaitingBinding>(
    FragmentPickUpWaitingBinding::bind,
    R.layout.fragment_pick_up_waiting
) {
    private lateinit var orderListRVAdapter: OrderListRVAdapter
    private val viewModel : OrderListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderListRVAdapter = OrderListRVAdapter()

        binding.bookRv.apply {
            adapter = orderListRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.orderList.observe(viewLifecycleOwner) {
            orderListRVAdapter.submitList(it.filter { order ->
                order.completed == false
            })
        }

        orderListRVAdapter.itemClickListener = object : OrderListRVAdapter.ItemClickListener {
            override fun onClick(view: View, data: Order, position: Int) {
                Log.d(TAG, "onClick: ${data}")

                val action = OrderListFragmentDirections.actionOrderListFragmentToOrderDetailFragment(data.id)
                findNavController().navigate(action)
            }
        }
    }
}