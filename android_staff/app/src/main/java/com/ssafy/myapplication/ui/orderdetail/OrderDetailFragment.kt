package com.ssafy.myapplication.ui.orderdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.databinding.FragmentOrderDetailBinding
import com.ssafy.myapplication.util.CommonUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

private const val TAG = "OrderDetailFragment"
class OrderDetailFragment : BaseFragment<FragmentOrderDetailBinding>(
    FragmentOrderDetailBinding::bind,
    R.layout.fragment_order_detail
) {
    private val viewModel : OrderDetailViewModel by viewModels()
    private lateinit var orderDetailRVAdapter: OrderDetailRVAdapter
    private val args : OrderDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObserver()

        viewModel.getOrderDetail(args.orderId)

        binding.floatingActionButton.setOnClickListener {
            viewModel.orderDetail.value?.let {
                val jsonObject = JsonObject().apply {
                    addProperty("userId", it.userId)
                    addProperty("orderId", it.id)
                }
                val requestBody = RequestBody.create(
                    "application/json".toMediaTypeOrNull(),
                    jsonObject.toString()
                )
                if(it.isPickup == false && it.isDone == true) {
                    // 수령완료되었습니다로 알림 보내기
                    viewModel.updateOrderStatePickup(requestBody)
                }else if(it.isPickup == false && it.isDone == false) {
                    viewModel.updateOrderStateDone(requestBody)
                    viewModel.setIsDone()
                    binding.floatingActionButton.setImageResource(R.drawable.icon_box)
                }
            }
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
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

            if(it.isDone == true) {
                binding.floatingActionButton.setImageResource(R.drawable.icon_box)
            } // 준비 완료 상태
        }
    }
}