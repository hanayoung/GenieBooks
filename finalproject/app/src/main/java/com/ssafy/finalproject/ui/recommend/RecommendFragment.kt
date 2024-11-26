package com.ssafy.finalproject.ui.recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentRecommendBinding
import com.ssafy.finalproject.ui.search.adapter.SearchAdapter

class RecommendFragment : BaseFragment<FragmentRecommendBinding>(
    FragmentRecommendBinding::bind,
    R.layout.fragment_recommend
) {

    private lateinit var adapter: SearchAdapter
    private val viewModel by viewModels<RecommendViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        initAdapter()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        adapter = SearchAdapter { id ->
            val action =
                RecommendFragmentDirections.actionRecommendFragmentToBookDetailFragment(id)
            findNavController().navigate(action)
        }
        binding.rv.adapter = adapter
    }

    private fun registerObserver() {
        viewModel.recommendBookList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.tvTotalCount.text = getString(R.string.search_total_count, it.size.toString())
        }
    }
}