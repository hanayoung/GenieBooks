package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentOrderDetailBinding
import com.ssafy.finalproject.ui.mypage.OrderDetailViewModel
import com.ssafy.finalproject.ui.mypage.adapter.OrderDetailRVAdapter
import com.ssafy.finalproject.util.CommonUtils
import com.ssafy.finalproject.util.setOnSingleClickListener

private const val TAG = "OrderDetailFragment"
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>(
    FragmentOrderDetailBinding::bind,
    R.layout.fragment_order_detail
) {
    private val viewModel : OrderDetailViewModel by viewModels()
    private lateinit var orderDetailRVAdapter: OrderDetailRVAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: OrderDetailFragmentArgs by navArgs()

        initAdapter()
        initObserver()

        viewModel.getOrderDetail(args.orderId)

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.floatingActionButton.setOnSingleClickListener {
            viewModel.orderDetail.value?.id?.let {
                val action = OrderDetailFragmentDirections.actionOrderDetailFragmentToQRCreateDialog(it)
                findNavController().navigate(action)
            }

        }
    }

    private fun initAdapter() {
        orderDetailRVAdapter = OrderDetailRVAdapter()

        binding.rv.apply {
            adapter = orderDetailRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObserver(){
        viewModel.orderDetail.observe(viewLifecycleOwner) {
            Log.d(TAG, "initObserver: ${it.details}")
            orderDetailRVAdapter.submitList(it.details)
            binding.tvOrder.text = getString(R.string.order_detail_info, CommonUtils.dateformatYMDHM(it.orderTime), it.id)
            binding.tvPrice.text = CommonUtils.makeComma(it.payment)
            binding.tvPickupTime.text = CommonUtils.dateformatYMDHM(it.orderTime)
        }
    }
}