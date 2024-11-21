package com.ssafy.finalproject.ui.joincategory

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentJoinCategoryBinding
import com.ssafy.finalproject.util.Category

private const val TAG = "JoinCategoryFragment_싸피"
class JoinCategoryFragment : BaseFragment<FragmentJoinCategoryBinding>(
    FragmentJoinCategoryBinding::bind,
    R.layout.fragment_join_category
) {

    // 선택된 카테고리를 저장할 리스트
    private val selectedCategories = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 모든 TextView를 바인딩 객체로 접근
        val categoryViews = listOf(
            binding.tvFiction to Category.FICTION,
            binding.tvCooking to Category.COOKING,
            binding.tvScience to Category.SCIENCE,
            binding.tvHealth to Category.HEALTH,
            binding.tvHiandsoc to Category.HIANDSOC,
            binding.tvTravel to Category.TRAVEL,
            binding.tvBuandec to Category.BUANDEC,
            binding.tvCrandhoandfi to Category.CRANDHOANDFI,
            binding.tvTeanden to Category.TEANDEN,
            binding.tvReligion to Category.RELIGION,
            binding.tvPoandli to Category.POANDLI,
            binding.tvArtandper to Category.ARTANDPER,
            binding.tvHoandho to Category.HOANDHO,
            binding.tvComputer to Category.COMPUTER,
            binding.tvHuman to Category.HUMAN,
            binding.tvSelfHelp to Category.SELFHELP,
            binding.tvPoandso to Category.POANDSO,
            binding.tvForeign to Category.FOREIGN
        )

        // 각 TextView에 클릭 리스너 설정
        categoryViews.forEach { (textView, category) ->
            textView.setOnClickListener {
                handleCategorySelection(textView, category)
            }
        }
    }

    // 카테고리 선택 처리
    private fun handleCategorySelection(textView: TextView, category: Category) {
        val categoryName = category.name
        if (selectedCategories.contains(categoryName)) {
            // 이미 선택된 경우 해제
            selectedCategories.remove(categoryName)
            val colorFromResources = ContextCompat.getColor(requireContext(), R.color.text)
            textView.setTextColor(colorFromResources)
        } else {
            // 선택되지 않은 경우 추가
            selectedCategories.add(categoryName)
            val colorFromResources = ContextCompat.getColor(requireContext(), R.color.primary)
            textView.setTextColor(colorFromResources)
        }
        Log.d(TAG, "handleCategorySelection: $selectedCategories")
    }
}