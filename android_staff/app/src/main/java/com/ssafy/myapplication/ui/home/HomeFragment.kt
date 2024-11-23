package com.ssafy.myapplication.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.myapplication.R
import com.ssafy.myapplication.base.BaseFragment
import com.ssafy.myapplication.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPickupList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_orderListFragment)
        }
    }
}