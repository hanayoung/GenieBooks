package com.ssafy.finalproject.ui.gift

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.BaseFragment
import com.ssafy.finalproject.databinding.FragmentMakeGiftCardBinding
import com.ssafy.finalproject.util.PermissionChecker

private const val TAG = "GiftCardFragment"
class MakeGiftCardFragment : BaseFragment<FragmentMakeGiftCardBinding>(
    FragmentMakeGiftCardBinding::bind,
    R.layout.fragment_make_gift_card
) {
    private val checker = PermissionChecker(this)
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val intent = checkNotNull(result.data)
                val imageUri = intent.data // 갤러리에서 선택한 사진 받아옴

                Glide.with(requireContext())
                    .load(imageUri)
                    .centerCrop()
                    .into(binding.image)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddImg.setOnClickListener {
            // 갤러리 연결
            val intent = Intent().also { intent ->
                intent.type = "image/"
                intent.action = Intent.ACTION_GET_CONTENT
            }
            launcher.launch(intent)
        }

        binding.btnSendGift.setOnClickListener {
            // 서버로 선물카드 전송 + 구매 목록 전송
        }
    }

    private fun checkPermission(){
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
}