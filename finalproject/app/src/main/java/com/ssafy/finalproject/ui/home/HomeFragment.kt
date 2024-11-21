package com.ssafy.finalproject.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentHomeBinding
import com.ssafy.finalproject.ui.home.adapter.BookVPAdapter
import com.ssafy.finalproject.ui.home.model.GoogleBook

private const val TAG = "HomeFragment_싸피"
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {
    private lateinit var bookVPAdapter: BookVPAdapter
    private val offsetBetweenPages =
        resources.getDimensionPixelOffset(R.dimen.offsetBetweenPages).toFloat()
    private val viewModel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookVPAdapter = BookVPAdapter()

        initAdapter()

        viewModel.bookList.observe(viewLifecycleOwner){
            bookVPAdapter.submitList(it)
        }

    }

    private fun initAdapter() {
        binding.bookVP.apply {
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
                }
            })
        }

        bookVPAdapter.itemClickListener = object : BookVPAdapter.ItemClickListener {
            override fun onClick(view: View, data: GoogleBook, position: Int) {
                // 책 상세화면으로 이동
            }
        }
    }
}