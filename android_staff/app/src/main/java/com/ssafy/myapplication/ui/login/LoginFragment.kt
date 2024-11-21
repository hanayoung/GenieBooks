package com.ssafy.myapplication.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnJoin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_joinFragment)
        }
    }
}