package com.ssafy.finalproject.ui.categorydetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentCategoryDetailBinding
import com.ssafy.finalproject.ui.categorydetail.adapter.CategoryDetailAdapter

class CategoryDetailFragment : BaseFragment<FragmentCategoryDetailBinding>(
    FragmentCategoryDetailBinding::bind,
    R.layout.fragment_category_detail
) {

    private lateinit var adapter : CategoryDetailAdapter
    private val viewModel : CategoryDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: CategoryDetailFragmentArgs by navArgs()
        val category = args.category
        val categoryKr = args.categoryKr

        binding.toolbarTitle.text = categoryKr

        initAdapter()
        registerObserver()
        viewModel.getAllCategoryBooks(category)
    }

    private fun initAdapter() {
        adapter = CategoryDetailAdapter()
        binding.rv.adapter = adapter
    }

    private fun registerObserver() {
        viewModel.bookList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }
}