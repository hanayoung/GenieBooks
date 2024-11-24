package com.ssafy.finalproject.ui.gift.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentMyGiftCardBinding
import com.ssafy.finalproject.util.CommonUtils
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class MyGiftCardFragment : BaseFragment<FragmentMyGiftCardBinding>(
    FragmentMyGiftCardBinding::bind,
    R.layout.fragment_my_gift_card
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvMainTitle.text = getString(R.string.gift_card_main_title, "즐거운 고라니")

        val localDate = LocalDate.now()
        binding.tvGiftCardTitle.text = getString(
            R.string.gift_card_title, CommonUtils.dateformatYMD(
                Date.from(
                    localDate.atStartOfDay(
                        ZoneId.systemDefault()
                    ).toInstant()
                )
            ),"즐거운 고라니")

        // 이후에 데이터 전달받아서 적용하기
    }
}