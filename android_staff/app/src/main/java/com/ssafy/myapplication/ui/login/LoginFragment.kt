package com.ssafy.myapplication.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.data.model.Staff
import com.ssafy.myapplication.databinding.FragmentLoginBinding

private const val TAG = "LoginFragment_싸피"

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()

        binding.btnLogin.setOnClickListener {
            if (binding.etId.text.toString().isNotEmpty() && binding.etPwd.text.toString()
                    .isNotEmpty()
            ) {
                Log.d(TAG, "onViewCreated: ${binding.etId.text.toString()}")
                viewModel.login(
                    Staff(
                        binding.etId.text.toString(),
                        "",
                        binding.etPwd.text.toString()
                    )
                )
            }
        }

        binding.btnJoin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_joinFragment)
        }
    }

    private fun registerObserver() {
        viewModel.isLoginSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Log.d(TAG, "registerObserver: 성공")
                // 화면 이동
            } else {
                showToast("ID와 비밀번호를 확인해주세요")
            }
        }
    }
}