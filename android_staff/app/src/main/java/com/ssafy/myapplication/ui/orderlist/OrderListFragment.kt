package com.ssafy.myapplication.ui.orderlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.databinding.FragmentOrderListBinding

private const val TAG = "OrderListFragment"
class OrderListFragment : BaseFragment<FragmentOrderListBinding>(
    FragmentOrderListBinding::bind,
    R.layout.fragment_order_list
) {
    private val viewModel : OrderListViewModel by viewModels()
    private lateinit var orderListRVAdapter : OrderListRVAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        initAdapter()

        initObserver()
    }

    private fun initAdapter() {
        orderListRVAdapter = OrderListRVAdapter()

        binding.rv.apply {
            adapter = orderListRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        orderListRVAdapter.itemClickListener = object : OrderListRVAdapter.ItemClickListener{
            override fun onClick(view: View, data: Order, position: Int) {
                val action = OrderListFragmentDirections.actionOrderListFragmentToOrderDetailFragment(data.id)
                findNavController().navigate(action)
            }

        }
    }

    private fun initObserver() {
        viewModel.orderList.observe(viewLifecycleOwner) {
            val list = it.filter { order ->
                order.pickup == false
            }
            orderListRVAdapter.submitList(list)
            // 비어있을 시 뭐라도 띄우기 빈 거라고
        }
    }
}