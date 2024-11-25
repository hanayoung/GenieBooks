package com.ssafy.finalproject.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.NonNull
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.databinding.DialogGiftCardCheckBinding
import com.ssafy.finalproject.databinding.DialogSignoutBinding
import com.ssafy.finalproject.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignOutDialog : DialogFragment() {
    private var _binding: DialogSignoutBinding? = null
    private val binding : DialogSignoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogSignoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialog()

        binding.btnPos.setOnClickListener {
            ApplicationClass.sharedPreferencesUtil.clear()
            // 로그아웃
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.btnNeg.setOnClickListener {
            // 머무르기
            findNavController().popBackStack()
        }
    }

    private fun setDialog() {
        val displayMetrics = resources.displayMetrics
        val widthPixels = displayMetrics.widthPixels

        val params = dialog?.window?.attributes
        params?.width = (widthPixels * 0.8).toInt()
        params?.height = ((widthPixels * 0.8).toInt() / 1.5).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}