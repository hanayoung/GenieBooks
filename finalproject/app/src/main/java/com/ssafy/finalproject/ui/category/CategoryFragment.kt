package com.ssafy.finalproject.ui.category

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.Category
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentCategoryBinding

private const val TAG = "CategoryFragment_싸피"

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(
    FragmentCategoryBinding::bind,
    R.layout.fragment_category
) {
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
                val action =
                    CategoryFragmentDirections.actionCategoryFragmentToCategoryDetailFragment(
                        category.name,
                        category.getCategoryKr()
                    )
                findNavController().navigate(action)
            }
        }

    }
}