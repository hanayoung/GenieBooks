package com.ssafy.finalproject.ui.gift.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.data.model.dto.Order
import com.ssafy.finalproject.databinding.FragmentGiftCardListBinding
import com.ssafy.finalproject.ui.gift.GiftCardListViewModel
import com.ssafy.finalproject.ui.gift.adapter.GiftCardListRVAdapter
import com.ssafy.finalproject.ui.mypage.adapter.OrderListRVAdapter

private const val TAG = "GiftCardListFragment"
class GiftCardListFragment : BaseFragment<FragmentGiftCardListBinding>(
    FragmentGiftCardListBinding::bind,
    R.layout.fragment_gift_card_list
) {
    private lateinit var giftCardListRVAdapter: GiftCardListRVAdapter
    private val viewModel : GiftCardListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObserver()

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        giftCardListRVAdapter = GiftCardListRVAdapter()

        binding.rv.apply {
            adapter = giftCardListRVAdapter
        }

        giftCardListRVAdapter.itemClickListener = object : GiftCardListRVAdapter.ItemClickListener {
            override fun onClick(view: View, data: GiftCard, position: Int) {
                val action = GiftCardListFragmentDirections.actionGiftCardListFragmentToMyGiftCardFragment(data)
                findNavController().navigate(action)
            }
        }
    }

    private fun initObserver() {
        viewModel.giftCardList.observe(viewLifecycleOwner){
            giftCardListRVAdapter.submitList(it)
        }
    }
}