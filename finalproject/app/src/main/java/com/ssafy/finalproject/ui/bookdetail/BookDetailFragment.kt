package com.ssafy.finalproject.ui.bookdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.databinding.FragmentBookDetailBinding
import com.ssafy.finalproject.util.CommonUtils
import jp.wasabeef.glide.transformations.BlurTransformation

private const val TAG = "BookDetailFragment_싸피"

class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(
    FragmentBookDetailBinding::bind,
    R.layout.fragment_book_detail
) {

    private val viewModel by viewModels<BookDetailViewModel>()
    private val args: BookDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displayMetrics = requireContext().resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels
        val itemHeight = (screenHeight * 0.3).toInt()
        binding.ivBookBg.layoutParams.height = itemHeight

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        registerObserver()
        Log.d(TAG, "onViewCreated: ${args.bookId}")
        viewModel.getBookById(args.bookId)
    }

    private fun registerObserver() {
        viewModel.book.observe(viewLifecycleOwner) {
            setBookInfo(it)
        }
    }

    private fun setBookInfo(it: GoogleBook) {
        val multiOptions = RequestOptions().transform(
            FitCenter(),
            BlurTransformation(20, 1)
        )

        binding.toolbarTitle.text = it.volumeInfo?.title
        Glide.with(this)
            .load(it.volumeInfo?.imageLinks?.thumbnail)
            .apply(multiOptions)
            .into(binding.ivBookBg)
        Glide.with(this)
            .load(it.volumeInfo?.imageLinks?.thumbnail)
            .placeholder(R.drawable.book_no_img)
            .into(binding.ivBook)
        binding.tvTitle.text = it.volumeInfo?.title
        val author = it.volumeInfo?.authors?.get(0)
        val publisher = it.volumeInfo?.publisher
        val publishedDate = it.volumeInfo?.publishedDate
        binding.tvInfo.text = getString(R.string.book_info, author, publisher, publishedDate)
        binding.tvPrice.text = CommonUtils.makeComma(it.saleInfo?.listPrice?.amount ?: 0)
        val point = CommonUtils.calculatePoints(it.saleInfo?.listPrice?.amount ?: 0)
        binding.tvPointValue.text = getString(R.string.point, CommonUtils.makeComma(point))
        binding.tvSummaryValue.text = it.volumeInfo?.description
    }
}