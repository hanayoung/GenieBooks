package com.ssafy.myapplication.ui.join

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.data.model.Staff
import com.ssafy.myapplication.databinding.FragmentJoinBinding
import com.ssafy.myapplication.util.setOnSingleClickListener

private const val TAG = "JoinFragment"

class JoinFragment : BaseFragment<FragmentJoinBinding>(
    FragmentJoinBinding::bind,
    R.layout.fragment_join
) {

    private val viewModel: JoinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        setupNextButton()
    }

    private fun setupNextButton() {
        binding.btnNext.setOnSingleClickListener {
            Log.d(TAG, "setupNextButton: ")
            if (!validateInputs()) return@setOnSingleClickListener
            viewModel.getIsUsedId(binding.inputLayoutId.editText?.text.toString())
        }
    }

    private fun validateInputs(): Boolean {
        val id = binding.inputLayoutId.editText?.text.toString()
        val pwd = binding.inputLayoutPwd.editText?.text.toString()
        val nickname = binding.inputLayoutNickName.editText?.text.toString()

        if (id.isBlank() || pwd.isBlank() || nickname.isBlank()) {
            showToast("모든 항목을 채워주세요")
            return false
        }

        return true
    }

    private fun join() {
        val id = binding.inputLayoutId.editText?.text.toString()
        val pwd = binding.inputLayoutPwd.editText?.text.toString()
        val nickname = binding.inputLayoutNickName.editText?.text.toString()

        val staff = Staff(
            id = id,
            nickname = nickname,
            pwd = pwd
        )

        viewModel.join(staff)
    }

    private fun registerObserver() {
        viewModel.isUsedId.observe(viewLifecycleOwner) { isUsed ->
            if (!isUsed) {
                join()
            } else {
                binding.inputLayoutId.error = "사용할 수 없는 ID입니다."
            }
        }

        viewModel.isJoinSuccess.observe(viewLifecycleOwner) {
            if (it) {
                showToast("회원 가입에 성공하였습니다.")
                findNavController().navigate(R.id.action_joinFragment_to_loginFragment)
            } else {
                showToast("회원 가입에 실패하였습니다. 다시 시도해주세요.")
            }
        }
    }
}