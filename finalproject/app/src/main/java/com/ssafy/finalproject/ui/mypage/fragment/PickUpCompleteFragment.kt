package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.finalproject.databinding.FragmentPickUpCompleteBinding
import com.ssafy.finalproject.ui.mypage.OrderListViewModel
import com.ssafy.finalproject.ui.mypage.adapter.OrderListRVAdapter

private const val TAG = "PickUpCompleteFragment"
class PickUpCompleteFragment : BaseFragment<FragmentPickUpCompleteBinding>(
    FragmentPickUpCompleteBinding::bind,
    R.layout.fragment_pick_up_complete
) {
    private lateinit var orderListRVAdapter: OrderListRVAdapter
    private val viewModel : OrderListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderListRVAdapter = OrderListRVAdapter()

        binding.bookRv.apply {
            adapter = orderListRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.orderList.observe(viewLifecycleOwner) {
            orderListRVAdapter.submitList(it.filter { order ->
                order.completed == true
            })
        }

        orderListRVAdapter.itemClickListener = object : OrderListRVAdapter.ItemClickListener {
            override fun onClick(view: View, data: Order, position: Int) {
                Log.d(TAG, "onClick: ${data}")
            }
        }
    }
}