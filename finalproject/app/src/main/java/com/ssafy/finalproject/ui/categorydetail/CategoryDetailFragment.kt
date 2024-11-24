package com.ssafy.finalproject.ui.categorydetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentCategoryDetailBinding
import com.ssafy.finalproject.ui.categorydetail.adapter.CategoryDetailAdapter

private const val TAG = "CategoryDetailFragment"

class CategoryDetailFragment : BaseFragment<FragmentCategoryDetailBinding>(
    FragmentCategoryDetailBinding::bind,
    R.layout.fragment_category_detail
) {

    private lateinit var adapter: CategoryDetailAdapter
    private val viewModel: CategoryDetailViewModel by viewModels()
    private val args: CategoryDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val category = args.category
        val categoryKr = args.categoryKr

        binding.toolbarTitle.text = categoryKr

        initAdapter()
        registerObserver()
        viewModel.getAllCategoryBooks(category)

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        val loadingAnimationConfig = Config.Builder()
            .autoplay(true)
            .speed(1f)
            .loop(true)
            .source(DotLottieSource.Asset("loading_animation.lottie"))
            .useFrameInterpolation(true)
            .playMode(Mode.FORWARD)
            .build()

        binding.loadingAnimation.load(loadingAnimationConfig)
    }

    private fun initAdapter() {
        adapter = CategoryDetailAdapter { id ->
            Log.d(TAG, "initAdapter: ")
            val action =
                CategoryDetailFragmentDirections.actionCategoryDetailFragmentToBookDetailFragment(id)
            findNavController().navigate(action)
        }
        binding.rv.adapter = adapter
    }

    private fun registerObserver() {
        viewModel.bookList.observe(viewLifecycleOwner) {
            it?.let {
                binding.loadingAnimation.visibility = View.GONE
                adapter.submitList(it)
            }
        }
    }
}