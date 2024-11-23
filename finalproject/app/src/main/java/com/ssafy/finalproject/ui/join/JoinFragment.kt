package com.ssafy.finalproject.ui.join

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.Gender
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.Customer
import com.ssafy.finalproject.databinding.FragmentJoinBinding
import com.ssafy.finalproject.util.setOnSingleClickListener

class JoinFragment : BaseFragment<FragmentJoinBinding>(
    FragmentJoinBinding::bind,
    R.layout.fragment_join
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnSingleClickListener {
            findNavController().popBackStack()
        }

        var isMan = true
        // Male 클릭 시 상태 변경
        binding.ivMale.setOnClickListener {
            binding.ivMale.setImageResource(R.drawable.ic_male_checked)  // 남성 선택 이미지
            binding.ivFemale.setImageResource(R.drawable.ic_female)  // 여성 비선택 이미지
            isMan = true
        }

        // Female 클릭 시 상태 변경
        binding.ivFemale.setOnClickListener {
            binding.ivFemale.setImageResource(R.drawable.ic_female_checked)  // 여성 선택 이미지
            binding.ivMale.setImageResource(R.drawable.ic_male)  // 남성 비선택 이미지
            isMan = false
        }

        binding.btnNext.setOnSingleClickListener {
            binding.inputLayoutId.editText?.let { id ->
                binding.inputLayoutPwd.editText?.let {  pwd ->
                    binding.inputLayoutNickName.editText?.let { nickname ->
                        binding.inputLayoutAge.editText?.let {  age ->
                            if(id.text.toString().isNotEmpty() && pwd.text.toString().isNotEmpty() && nickname.text.toString().isNotEmpty() && age.text.toString().isNotEmpty()) {

                                val gender = if (isMan) {
                                    Gender.MAN.name
                                } else {
                                    Gender.WOMAN.name
                                }

                                // 모든 데이터 넘기기
                                val action = JoinFragmentDirections.actionJoinFragmentToJoinCategoryFragment(
                                    Customer(
                                        id.text.toString(),
                                        nickname.text.toString(),
                                        pwd.text.toString(),
                                        age.text.toString().toInt(),
                                        gender,
                                        null
                                    )
                                )
                                findNavController().navigate(action)

                            }else{
                                showToast("모든 항목을 채워주세요")
                            }
                        }
                    }

                }
            }
        }
    }
}