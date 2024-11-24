package com.ssafy.finalproject.ui.shoppingcart

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.ShoppingCartBook
import com.ssafy.finalproject.databinding.FragmentShoppingCartBinding
import com.ssafy.finalproject.ui.MainViewModel
import com.ssafy.finalproject.ui.shoppingcart.adapter.ShoppingCartAdapter
import com.ssafy.finalproject.util.CommonUtils

private const val TAG = "ShoppingCartFragment_싸피"
class ShoppingCartFragment: BaseFragment<FragmentShoppingCartBinding>(
    FragmentShoppingCartBinding::bind,
    R.layout.fragment_shopping_cart
) {

    private val activityViewModel by activityViewModels<MainViewModel>()
    lateinit var adapter: ShoppingCartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        initAdapter()
    }

    private fun initAdapter() {
        adapter = ShoppingCartAdapter()
        adapter.itemClickListener = object : ShoppingCartAdapter.ItemClickListener {
            override fun onClickDelete(book: ShoppingCartBook) {
                activityViewModel.removeShoppingCart(book)
            }

            override fun onClickUpdate() {
                updateTotal(adapter.currentList)
            }
        }
        binding.rv.adapter = adapter
    }

    private fun registerObserver() {
        activityViewModel.bookList.observe(viewLifecycleOwner) { bookList ->
            adapter.submitList(bookList?.toList())
            updateTotal(bookList)
        }
    }

    private fun updateTotal(bookList: MutableList<ShoppingCartBook>) {
        var totalCount = 0
        var totalPrice = 0
        bookList.forEach {
            totalCount += it.count
            totalPrice += (it.price * it.count)
        }
        binding.tvTotalCount.text = getString(R.string.total_count, totalCount.toString())
        binding.tvTotalPrice.text = CommonUtils.makeComma(totalPrice)
    }
}