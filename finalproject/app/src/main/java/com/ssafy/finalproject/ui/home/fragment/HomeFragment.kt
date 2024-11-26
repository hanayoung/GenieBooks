package com.ssafy.finalproject.ui.home.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.GoogleBook
import com.ssafy.finalproject.databinding.FragmentHomeBinding
import com.ssafy.finalproject.ui.home.HomeViewModel
import com.ssafy.finalproject.ui.home.adapter.BookVPAdapter
import com.ssafy.finalproject.util.CommonUtils

private const val TAG = "HomeFragment"
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {
    private lateinit var bookVPAdapter: BookVPAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadingAnimation.playAnimation()
        binding.iconCalendar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_attendanceFragment)
        }

        binding.btnShoppingCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shoppingCartFragment)
        }

        binding.searchBar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        bookVPAdapter = BookVPAdapter()

        initAdapter()


        viewModel.bookList.observe(viewLifecycleOwner) { it ->
            if(it.isNotEmpty()){
                binding.loadingAnimation.visibility = View.GONE
                binding.loadingAnimation.pauseAnimation()
                bookVPAdapter.submitList(it)
                it[0].volumeInfo?.let { book ->
                    binding.tvTitle.text = book.title
                    binding.tvAuthor.text = book.authors.joinToString(separator = ", ")
                }

                it[0].saleInfo?.listPrice?.amount?.let { price ->
                    binding.tvPrice.text = CommonUtils.makeComma(price)
                }

                it[0].searchInfo?.textSnippet?.let { textSnippet ->
                    binding.tvOverview.text = textSnippet
                }

                binding.loadingAnimation.visibility = View.GONE
                binding.bookVP.visibility = View.VISIBLE
            }
        }
    }

    private fun initAdapter() {
        binding.bookVP.apply {
            val offsetBetweenPages =
                resources.getDimensionPixelOffset(R.dimen.offsetBetweenPages).toFloat()
            adapter = bookVPAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 4
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.right = offsetBetweenPages.toInt()
                    outRect.left = offsetBetweenPages.toInt()
                }
            })

            setPageTransformer { page, position ->
                val myOffset = position * -(5 * offsetBetweenPages)
                if (position < -1) {
                    page.translationX = -myOffset
                } else if (position <= 1) {
                    // Paging 시 Y축 Animation 배경색을 약간 연하게 처리
                    val scaleFactor = 0.85f.coerceAtLeast(1 - kotlin.math.abs(position))
                    page.translationX = myOffset
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                    page.alpha = scaleFactor
                } else {
                    page.alpha = 0f
                    page.translationX = myOffset
                }
            }
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    // 책 정보 변경

                    if (viewModel.bookList.value != null) {
                        initializeTextView()

                        viewModel.bookList.value?.get(position)?.volumeInfo?.let { book ->
                            binding.tvTitle.text = book.title
                            binding.tvAuthor.text = book.authors.joinToString(separator = ", ")
                        }

                        viewModel.bookList.value?.get(position)?.saleInfo?.listPrice?.amount?.let { price ->
                            binding.tvPrice.text = CommonUtils.makeComma(price)
                        }

                        viewModel.bookList.value?.get(position)?.searchInfo?.textSnippet?.let { textSnippet ->
                            binding.tvOverview.text = textSnippet
                        }
                    }

                }
            })
        }

        bookVPAdapter.itemClickListener = object : BookVPAdapter.ItemClickListener {
            override fun onClick(view: View, data: GoogleBook, position: Int) {
                // 책 상세화면으로 이동
                val action =
                    HomeFragmentDirections.actionHomeFragmentToBookDetailFragment(data.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun initializeTextView() {
        binding.tvTitle.text = ""
        binding.tvPrice.text = ""
        binding.tvOverview.text = ""
        binding.tvAuthor.text = ""
    }
}