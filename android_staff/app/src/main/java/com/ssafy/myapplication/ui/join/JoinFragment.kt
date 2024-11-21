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

class JoinFragment: BaseFragment<FragmentJoinBinding>(
    FragmentJoinBinding::bind,
    R.layout.fragment_join
) {

    private val viewModel: JoinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()

        binding.btnNext.setOnSingleClickListener {
            binding.inputLayoutId.editText?.let { id ->
                binding.inputLayoutPwd.editText?.let {  pwd ->
                    binding.inputLayoutNickName.editText?.let { nickname ->
                        if(id.text.toString().isNotEmpty() && pwd.text.toString().isNotEmpty() && nickname.text.toString().isNotEmpty()) {
                            viewModel.join(Staff(id.text.toString(), pwd.text.toString(), nickname.text.toString()))
                        }else{
                            showToast("모든 항목을 채워주세요")
                        }
                    }

                }
            }
        }
    }

    private fun registerObserver() {
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