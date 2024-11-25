package com.ssafy.finalproject.ui.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.data.model.dto.OrderDetail
import com.ssafy.finalproject.data.remote.RetrofitUtil
import com.ssafy.finalproject.databinding.DialogGiftCardCheckBinding
import com.ssafy.finalproject.ui.MainViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

private const val TAG = "GiftCardCheckDialog"
class GiftCardCheckDialog: DialogFragment(){
    private var _binding: DialogGiftCardCheckBinding? = null
    private val binding : DialogGiftCardCheckBinding
        get() = _binding!!

    private val activityViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogGiftCardCheckBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialog()

        binding.btnPos.setOnClickListener {
            findNavController().navigate(R.id.action_giftCardCheckDialog_to_makeGiftCardFragment)
        }

        binding.btnNeg.setOnClickListener {
            // 주문 확인
            lifecycleScope.launch {
                runCatching {
                    var payment = 0
                    activityViewModel.bookList.value?.forEach{
                        payment += it.price * it.count
                    }
                    val userId = ApplicationClass.sharedPreferencesUtil.getUserId()
                    val details = activityViewModel.bookList.value?.map {
                        OrderDetail((it.id).toLong(), it.count)
                    } ?: emptyList()
                    if(details.isEmpty()) throw Exception("details is empty")
                    val gson = Gson()
                    val detailsJsonArray = gson.toJsonTree(details).asJsonArray
                    val jsonObject = JsonObject().apply {
                        addProperty("userId", userId)
                        addProperty("payment", payment)
                        add("details", detailsJsonArray)
                    }
                    val requestBody = RequestBody.create(
                        "application/json".toMediaTypeOrNull(),
                        jsonObject.toString()
                    )
                    RetrofitUtil.orderService.makeOrder(requestBody)
                }.onSuccess {
                    if(it > -1) {
                       Toast.makeText(requireContext(), "주문해주셔서 감사합니다🥰",Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_giftCardCheckDialog_to_homeFragment)
                        activityViewModel.clearShoppingCart()
                    }else{
                        Toast.makeText(requireContext(), "주문이 실패하였습니다. 다시 시도해주세요😥",Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }

                }.onFailure {
                    Log.d(TAG, "onViewCreated: fail ${it.message}")
                }
            }
        }
    }

    private fun setDialog() {
        val displayMetrics = resources.displayMetrics
        val widthPixels = displayMetrics.widthPixels

        val params = dialog?.window?.attributes
        params?.width = (widthPixels * 0.8).toInt()
        params?.height = ((widthPixels * 0.8).toInt() / 1.5).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_small_bg)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}