package com.ssafy.finalproject.ui.mypage.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.Notification
import com.ssafy.finalproject.databinding.FragmentNotificationListBinding
import com.ssafy.finalproject.ui.mypage.NotificationViewModel
import com.ssafy.finalproject.ui.mypage.adapter.NotificationRVAdapter

class NotificationListFragment : BaseFragment<FragmentNotificationListBinding>(
    FragmentNotificationListBinding::bind,
    R.layout.fragment_notification_list
) {
    private lateinit var notificationListRVAdapter: NotificationRVAdapter
    private val viewModel : NotificationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initObserver()

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun initAdapter() {
        notificationListRVAdapter = NotificationRVAdapter()
        binding.notiRV.apply {
            adapter = notificationListRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        notificationListRVAdapter.itemClickListener =
            object : NotificationRVAdapter.ItemClickListener {
                override fun onClick(view: View, data: String, position: Int) {
                    val notiList = ApplicationClass.sharedPreferencesUtil.getNotice()
                    notiList.removeAt(position)
                    ApplicationClass.sharedPreferencesUtil.setNotice(notiList)
                    notificationListRVAdapter.submitList(notiList)
                    // 해당 알림 삭제
                }
            }
    }

    private fun initObserver() {
        viewModel.notiList.observe(viewLifecycleOwner) {
            notificationListRVAdapter.submitList(it)
        }
    }
}