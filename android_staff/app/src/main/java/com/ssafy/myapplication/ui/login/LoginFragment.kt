package com.ssafy.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.data.model.Staff
import com.ssafy.myapplication.databinding.FragmentLoginBinding
import com.ssafy.myapplication.ui.MainActivity
import com.ssafy.myapplication.util.setOnSingleClickListener

private const val TAG = "LoginFragment_싸피"

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()

        binding.btnLogin.setOnSingleClickListener {
            if (binding.etId.text.toString().isNotEmpty() && binding.etPwd.text.toString()
                    .isNotEmpty()
            ) {
                viewModel.login(
                    Staff(
                        -1,
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
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                showToast("ID와 비밀번호를 확인해주세요")
            }
        }
    }
}