package com.ssafy.finalproject.ui.gift.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
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
    private val args : MyGiftCardFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvGiftCardTitle.text = getString(R.string.gift_card_title, CommonUtils.dateformatYMDHM(args.data.giftDate), args.data.title)
        binding.tvMainTitle.text = getString(R.string.gift_card_main_title, args.data.senderName)
        Glide.with(requireContext())
            .load(args.data.imgUrl)
            .centerCrop()
            .into(binding.image)

        binding.title.text = args.data.title
        binding.name.text = args.data.senderName
        binding.cardDescription.text = args.data.content

    }
}