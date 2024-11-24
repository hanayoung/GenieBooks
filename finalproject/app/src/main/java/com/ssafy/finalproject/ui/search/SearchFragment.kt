package com.ssafy.finalproject.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentSearchBinding
import com.ssafy.finalproject.ui.search.adapter.SearchAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::bind,
    R.layout.fragment_search
) {

    private lateinit var adapter: SearchAdapter
    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        initAdapter()

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.etSearch.addTextChangedListener { keyword ->
            viewModel.getBooksByKeyword(keyword.toString())
        }
    }

    private fun initAdapter() {
        adapter = SearchAdapter { id ->
            val action =
                SearchFragmentDirections.actionSearchFragmentToBookDetailFragment(id)
            findNavController().navigate(action)
        }
        binding.rv.adapter = adapter
    }

    private fun registerObserver() {
        viewModel.bookList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.tvTotalCount.text = getString(R.string.search_total_count, it.size.toString())
        }
    }
}