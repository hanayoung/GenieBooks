package com.ssafy.finalproject.ui.join

import android.os.Bundle
import android.view.View
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentJoinBinding

class JoinFragment : BaseFragment<FragmentJoinBinding>(
    FragmentJoinBinding::bind,
    R.layout.fragment_join
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Male 클릭 시 상태 변경
        binding.ivMale.setOnClickListener {
            binding.ivMale.setImageResource(R.drawable.ic_male_checked)  // 남성 선택 이미지
            binding.ivFemale.setImageResource(R.drawable.ic_female)  // 여성 비선택 이미지
        }

        // Female 클릭 시 상태 변경
        binding.ivFemale.setOnClickListener {
            binding.ivFemale.setImageResource(R.drawable.ic_female_checked)  // 여성 선택 이미지
            binding.ivMale.setImageResource(R.drawable.ic_male)  // 남성 비선택 이미지
        }
    }
}