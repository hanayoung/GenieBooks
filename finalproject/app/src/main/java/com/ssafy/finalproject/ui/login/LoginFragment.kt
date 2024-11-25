package com.ssafy.finalproject.ui.login

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.JsonObject
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.customerService
import com.ssafy.finalproject.databinding.FragmentLoginBinding
import com.ssafy.finalproject.util.setOnSingleClickListener
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

private const val TAG = "LoginFragment_싸피"
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {

    private val viewModel by viewModels<LoginViewModel>()
    private val id = ApplicationClass.sharedPreferencesUtil.getId()
    private val userId = ApplicationClass.sharedPreferencesUtil.getUserId()

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
                                ApplicationClass.sharedPreferencesUtil.apply {
                                    addUserId(it.cid)
                                    addId(it.id)
                                }

                                findNavController().apply {
                                    navigate(R.id.action_loginFragment_to_homeFragment)
                                }
                            }.onFailure {
                                Log.d(TAG, "onViewCreated: fail ${it.message}")
                                showToast("ID와 비밀번호를 확인해주세요")
                            }
                        }
                    }
                }
            }
        }

        binding.btnJoin.setOnSingleClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_joinFragment)
        }

        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        if (!id.equals("-1") && userId != -1) {
            activity?.intent?.let {
                getNFCData(it)
            }
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun getNFCData(intent: Intent) {
        val action = intent.action
        Log.d(TAG, "getNFCData: " + action)
        if (action == NfcAdapter.ACTION_NDEF_DISCOVERED) {

            val messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

            messages?.forEach {
                val message = it as NdefMessage
                message.records.forEach {
                    val type = String(it.type)
                    val payload = String(it.payload)

                    Log.d(TAG, "getNFCData: $type")
                    Log.d(TAG, "getNFCData: $payload")

                    if (type == "T") {
                        val giftCardId = String(it.payload)
                        Log.d(TAG, "getNFCData - giftCardId: $giftCardId")
                        viewModel.receiveGiftCard(
                            ApplicationClass.sharedPreferencesUtil.getUserId(),
                            giftCardId.toInt()
                        )
                    }
                }
            }
        }
    }

    private fun registerObserver() {
        viewModel.isReceiveSuccess.observe(viewLifecycleOwner) {
            showToast("선물 카드를 받았습니다.")
        }
    }
}