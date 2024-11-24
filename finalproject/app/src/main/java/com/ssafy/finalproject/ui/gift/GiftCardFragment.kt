package com.ssafy.finalproject.ui.gift

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentGiftCardBinding

class GiftCardFragment : BaseFragment<FragmentGiftCardBinding>(
    FragmentGiftCardBinding::bind,
    R.layout.fragment_gift_card
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddImg.setOnClickListener {
            // 갤러리 연결
        }

        binding.btnSendGift.setOnClickListener {
            // 서버로 선물카드 전송 + 구매 목록 전송
        }
    }
}