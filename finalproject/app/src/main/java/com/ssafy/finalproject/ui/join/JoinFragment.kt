package com.ssafy.finalproject.ui.join

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.Gender
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.Customer
import com.ssafy.finalproject.databinding.FragmentJoinBinding
import com.ssafy.finalproject.ui.EventObserver
import com.ssafy.finalproject.util.setOnSingleClickListener

class JoinFragment : BaseFragment<FragmentJoinBinding>(
    FragmentJoinBinding::bind, R.layout.fragment_join
) {

    private val viewModel by viewModels<JoinViewModel>()
    private var isMan = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        registerObservers()
    }

    private fun setupUI() {
        setupBackButton()
        setupGenderSelection()
        setupNextButton()
    }

    private fun setupBackButton() {
        binding.backBtn.setOnSingleClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupGenderSelection() {
        binding.ivMale.setOnClickListener {
            updateGenderSelection(isMaleSelected = true)
        }

        binding.ivFemale.setOnClickListener {
            updateGenderSelection(isMaleSelected = false)
        }
    }

    private fun updateGenderSelection(isMaleSelected: Boolean) {
        isMan = isMaleSelected
        binding.ivMale.setImageResource(
            if (isMaleSelected) R.drawable.ic_male_checked else R.drawable.ic_male
        )
        binding.ivFemale.setImageResource(
            if (isMaleSelected) R.drawable.ic_female else R.drawable.ic_female_checked
        )
    }

    private fun setupNextButton() {
        binding.btnNext.setOnSingleClickListener {
            if (!validateInputs()) return@setOnSingleClickListener
            viewModel.getIsUsedId(binding.inputLayoutId.editText?.text.toString())
        }
    }

    private fun validateInputs(): Boolean {
        val id = binding.inputLayoutId.editText?.text.toString()
        val pwd = binding.inputLayoutPwd.editText?.text.toString()
        val nickname = binding.inputLayoutNickName.editText?.text.toString()
        val ageText = binding.inputLayoutAge.editText?.text.toString()

        if (id.isBlank() || pwd.isBlank() || nickname.isBlank() || ageText.isBlank()) {
            showToast("모든 항목을 채워주세요")
            return false
        }

        if (ageText.toIntOrNull() == null) {
            showToast("유효한 나이를 입력해주세요")
            return false
        }

        return true
    }

    private fun moveToJoinCategory() {
        val id = binding.inputLayoutId.editText?.text.toString()
        val pwd = binding.inputLayoutPwd.editText?.text.toString()
        val nickname = binding.inputLayoutNickName.editText?.text.toString()
        val age = binding.inputLayoutAge.editText?.text.toString().toInt()

        val gender = if (isMan) Gender.MAN.name else Gender.WOMAN.name

        val customer = Customer(
            id = id,
            nickname = nickname,
            pwd = pwd,
            age = age,
            sex = gender,
            category = arrayListOf()
        )

        val action = JoinFragmentDirections.actionJoinFragmentToJoinCategoryFragment(customer)
        findNavController().navigate(action)
    }

    private fun registerObservers() {
        viewModel.isUsedId.observe(viewLifecycleOwner, EventObserver { isUsed ->
            if (!isUsed) {
                moveToJoinCategory()
            } else {
                binding.inputLayoutId.error = "사용할 수 없는 ID입니다."
            }
        })
    }
}
