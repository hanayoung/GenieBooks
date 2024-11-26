package com.ssafy.finalproject.ui.gift.fragment

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.model.Config
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.data.model.dto.GiftCard
import com.ssafy.finalproject.data.model.dto.GiftCardRequest
import com.ssafy.finalproject.databinding.FragmentMakeGiftCardBinding
import com.ssafy.finalproject.ui.EventObserver
import com.ssafy.finalproject.ui.gift.MakeGiftCardViewModel
import com.ssafy.finalproject.util.PermissionChecker
import com.ssafy.finalproject.util.setOnSingleClickListener
import java.util.Date

private const val TAG = "MakeGiftCardFragment"

class MakeGiftCardFragment : BaseFragment<FragmentMakeGiftCardBinding>(
    FragmentMakeGiftCardBinding::bind,
    R.layout.fragment_make_gift_card
) {
    private val checker = PermissionChecker(this)
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<MakeGiftCardViewModel>()
    private var isImageSelected = false
    private val userId = ApplicationClass.sharedPreferencesUtil.getUserId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == AppCompatActivity.RESULT_OK) {
                    val intent = checkNotNull(result.data)
                    val imageUri = intent.data // 갤러리에서 선택한 사진 받아옴

                    imageUri?.let {
                        viewModel.selectImage(it)
                        isImageSelected = true
                    }

                    Glide.with(requireContext())
                        .load(imageUri)
                        .centerCrop()
                        .into(binding.image)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingAnimation.visibility = View.GONE
        registerObserver()

        val loadingAnimationConfig = Config.Builder()
            .autoplay(true)
            .speed(1f)
            .loop(true)
            .source(DotLottieSource.Asset("gift_card_loading.lottie"))
            .useFrameInterpolation(true)
            .playMode(Mode.FORWARD)
            .build()

        binding.loadingAnimation.load(loadingAnimationConfig)

        binding.btnAddImg.setOnClickListener {
            // 갤러리 연결
            val intent = Intent().also { intent ->
                intent.type = "image/"
                intent.action = Intent.ACTION_GET_CONTENT
            }
            launcher.launch(intent)
        }

        binding.btnSendGift.setOnSingleClickListener {
            val title = binding.title.text.toString()
            val name = binding.name.text.toString()
            val description = binding.cardDescription.text.toString()

            if (title.isBlank() || name.isBlank() || description.isBlank()) {
                showToast("빈칸을 채워주세요.")
                return@setOnSingleClickListener
            }

            // 서버로 선물카드 전송 + 구매 목록 전송
            if (isImageSelected) {
                binding.loadingAnimation.visibility = View.VISIBLE

                val timeStamp = System.currentTimeMillis()
                viewModel.uploadImage(userId, timeStamp)
            } else {
                showToast("사진을 선택해주세요.")
            }
        }


    }

    private fun checkPermission() {
        /** permission check **/
        if (!checker.checkPermission(requireContext(), arrayOf(getRequiredPermission()))) {
            checker.setOnGrantedListener { //퍼미션 획득 성공일때
                Log.d(TAG, "checkPermission: permission granted")
            }
            checker.requestPermissionLauncher.launch(arrayOf(getRequiredPermission())) // 권한없으면 창 띄움
        } else { //이미 전체 권한이 있는 경우
            Log.d(TAG, "checkPermission: permission 이미 있음")
        }

        /** permission check **/
    }

    private fun getRequiredPermission(): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
    }

    private fun registerObserver() {
        viewModel.imagePathEvent.observe(viewLifecycleOwner, EventObserver {
            val title = binding.title.text.toString()
            val name = binding.name.text.toString()
            val description = binding.cardDescription.text.toString()

            val giftCard = GiftCardRequest(
                title = title,
                content = description,
                imgUrl = it,
                senderId = userId,
                senderName = name
            )

            viewModel.insertGiftCard(giftCard)
        })

        viewModel.isSendSuccess.observe(viewLifecycleOwner) {
            if (it) {
                showToast("주문해주셔서 감사합니다🥰")
                findNavController().navigate(R.id.action_makeGiftCardFragment_to_homeFragment)
            }
        }
    }
}