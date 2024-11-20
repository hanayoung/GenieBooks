package com.ssafy.finalproject.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.JsonObject
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.customerService
import com.ssafy.finalproject.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

private const val TAG = "LoginFragment_싸피"
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            binding.inputLayoutId.editText?.let { id ->
                binding.inputLayoutPwd.editText?.let {  pwd ->
                    if(id.text.toString().isNotEmpty() && pwd.text.toString().isNotEmpty()) {
                        lifecycleScope.launch {
                            runCatching {
                                val jsonObject = JsonObject().apply {
                                    addProperty("id", id.text.toString())
                                    addProperty("pwd", pwd.text.toString())
                                }
                                val requestBody = RequestBody.create(
                                    "application/json".toMediaTypeOrNull(),
                                    jsonObject.toString()
                                )
                                customerService.login(requestBody)
                            }.onSuccess {
                                Log.d(TAG, "onViewCreated: success $it")
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }.onFailure {
                                Log.d(TAG, "onViewCreated: fail ${it.message}")
                                showToast("ID와 비밀번호를 확인해주세요")
                            }
                        }


                    }
                }
            }
        }

        binding.btnJoin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_joinFragment)
        }
    }
}