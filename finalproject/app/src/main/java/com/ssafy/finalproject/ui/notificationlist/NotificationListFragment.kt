package com.ssafy.finalproject.ui.notificationlist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentNotificationListBinding

class NotificationListFragment : BaseFragment<FragmentNotificationListBinding>(
    FragmentNotificationListBinding::bind,
    R.layout.fragment_notification_list
)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}