package com.ssafy.finalproject.ui.bookdetail

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.data.model.dto.ShoppingCartBook
import com.ssafy.finalproject.databinding.FragmentBookDetailBinding
import com.ssafy.finalproject.ui.MainViewModel
import com.ssafy.finalproject.util.CommonUtils
import com.ssafy.finalproject.util.setOnSingleClickListener
import jp.wasabeef.glide.transformations.BlurTransformation

private const val TAG = "BookDetailFragment_싸피"

class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>(
    FragmentBookDetailBinding::bind,
    R.layout.fragment_book_detail
) {

    private val viewModel by viewModels<BookDetailViewModel>()
    private val activityViewModel by activityViewModels<MainViewModel>()
    private val args: BookDetailFragmentArgs by navArgs()
    private lateinit var book: GoogleBook

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
        viewModel.getBookById(args.bookId)

        binding.btnShoppingCart.setOnSingleClickListener {
            activityViewModel.addShoppingCart(
                book = ShoppingCartBook(
                    id = book.volumeInfo?.industryIdentifiers?.get(0)?.identifier ?: "",
                    imageUrl = book.volumeInfo?.imageLinks?.thumbnail ?: "",
                    title = book.volumeInfo?.title ?: "",
                    price = book.saleInfo?.listPrice?.amount ?: 0,
                    count = 1
                )
            )
            showToast("장바구니에 상품이 추가되었습니다.")
        }
    }

    private fun registerObserver() {
        viewModel.book.observe(viewLifecycleOwner) {
            book = it
            setBookInfo()
        }
    }

    private fun setBookInfo() {
        val multiOptions = RequestOptions().transform(
            FitCenter(),
            BlurTransformation(20, 1)
        )

        binding.toolbarTitle.text = book.volumeInfo?.title
        Glide.with(this)
            .load(book.volumeInfo?.imageLinks?.thumbnail)
            .apply(multiOptions)
            .into(binding.ivBookBg)
        Glide.with(this)
            .load(book.volumeInfo?.imageLinks?.thumbnail)
            .placeholder(R.drawable.book_no_img)
            .into(binding.ivBook)
        binding.tvTitle.text = book.volumeInfo?.title
        val author = book.volumeInfo?.authors?.get(0)
        val publisher = book.volumeInfo?.publisher
        val publishedDate = book.volumeInfo?.publishedDate
        binding.tvInfo.text = getString(R.string.book_info, author, publisher, publishedDate)
        binding.tvPrice.text = CommonUtils.makeComma(book.saleInfo?.listPrice?.amount ?: 0)
        val point = CommonUtils.calculatePoints(book.saleInfo?.listPrice?.amount ?: 0)
        binding.tvPointValue.text = getString(R.string.point, CommonUtils.makeComma(point))
        binding.tvSummaryValue.text = Html.fromHtml(book.volumeInfo?.description ?: "", Html.FROM_HTML_MODE_LEGACY)

        if(book.saleInfo == null) {
            binding.btnShoppingCart.visibility = View.GONE
            binding.tvPrice.text = "절판된 책입니다"
        }
    }
}