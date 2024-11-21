package com.ssafy.finalproject.ui.joincategory

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ssafy.finalproject.Category
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentJoinCategoryBinding
import com.ssafy.finalproject.util.setOnSingleClickListener

private const val TAG = "JoinCategoryFragment_싸피"

class JoinCategoryFragment : BaseFragment<FragmentJoinCategoryBinding>(
    FragmentJoinCategoryBinding::bind,
    R.layout.fragment_join_category
) {

    private val viewModel: JoinCategoryViewModel by viewModels<JoinCategoryViewModel>()

    // 선택된 카테고리를 저장할 리스트
    private val selectedCategories = arrayListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: JoinCategoryFragmentArgs by navArgs()
        val customer = args.customer

        binding.ivBack.setOnSingleClickListener {
            findNavController().popBackStack()
        }

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

        registerObserver()

        binding.btnNext.setOnSingleClickListener {
            if (selectedCategories.isNotEmpty()) {
                customer.category = selectedCategories
                viewModel.join(customer)
                Log.d(TAG, "onViewCreated: $customer")
            } else {
                showToast("카테고리를 한개 이상 선택해주세요.")
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

    private fun registerObserver() {
        viewModel.isJoinSuccess.observe(viewLifecycleOwner) {
            Log.d(TAG, "registerObserver: $it")
            if (it) {
                showToast("회원 가입에 성공하였습니다.")
                findNavController().navigate(R.id.action_joinCategoryFragment_to_loginFragment)
            } else {
                showToast("회원 가입에 실패하였습니다. 다시 시도해주세요.")
            }
        }
    }
}