package com.ssafy.finalproject.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseActivity
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.firebaseTokenService
import com.ssafy.finalproject.databinding.ActivityMainBinding
import com.ssafy.finalproject.util.PermissionChecker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity_싸피"

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    /** permission check **/
    private val checker = PermissionChecker(this)
    private val runtimePermissions = arrayOf(Manifest.permission.POST_NOTIFICATIONS)

    /** permission check **/

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        binding.bnv.setupWithNavController(navController)

        hideBottomNavigationView(navController)

        registerObserver()
        getNFCData(intent)

//        checkPermission()
    }

    private fun getNFCData(intent: Intent) {
        val action = intent.action
        Log.d(TAG, "getNFCData: $action")
        if (action == NfcAdapter.ACTION_NDEF_DISCOVERED) {

            val messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

            messages?.forEach {
                val message = it as NdefMessage
                message.records.forEach {
                    val type = String(it.type)
                    val payload = String(it.payload)

                    Log.d(TAG, "getNFCData: $type")
                    Log.d(TAG, "getNFCData: $payload")

                    if (type == "T") {
                        val giftCardId = String(it.payload)
                        Log.d(TAG, "getNFCData - giftCardId: $giftCardId")
                        viewModel.receiveGiftCard(
                            ApplicationClass.sharedPreferencesUtil.getUserId(),
                            giftCardId.toInt()
                        )
                    }
                }
            }
        }
    }

    private fun registerObserver() {
        viewModel.isReceiveSuccess.observe(this) {
            showToast("선물이 도착했어요!😊")
            navController.navigate(R.id.giftCardListFragment)
        }
    }

    private fun hideBottomNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnv.visibility = when (destination.id) {
                R.id.loginFragment -> View.GONE
                R.id.joinFragment -> View.GONE
                R.id.joinCategoryFragment -> View.GONE
                R.id.homeFragment -> View.VISIBLE
                R.id.categoryFragment -> View.VISIBLE
                R.id.myPageFragment -> View.VISIBLE
                else -> View.VISIBLE
            }
        }
    }

    private fun checkPermission() {
        /** permission check **/
        if (!checker.checkPermission(this, runtimePermissions)) {
            checker.setOnGrantedListener { //퍼미션 획득 성공일때
                init()
            }

            checker.requestPermissionLauncher.launch(runtimePermissions) // 권한없으면 창 띄움
        } else { //이미 전체 권한이 있는 경우
            init()
        }
        /** permission check **/
    }

    private fun init() {
        initFCM()
        createNotificationChannel(channel_id, "smart_store")
    }

    private fun initFCM() {
        // FCM 토큰 수신
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d(TAG, "FCM 토큰 얻기에 실패하였습니다.", task.exception)
                return@OnCompleteListener
            }
            // token log 남기기
            Log.d(TAG, "token: ${task.result ?: "task.result is null"}")
            if (task.result != null) {
                uploadToken(task.result!!)
            }
        })
    }

    // Notification 수신을 위한 체널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(NotificationChannel(id, name, importance))
        }
    }


    companion object {
        // Notification Channel ID
        const val channel_id = "smart_store_channel"

        // ratrofit  수업 후 network 에 업로드 할 수 있도록 구성
        fun uploadToken(token: String) {
            // 새로운 토큰 수신 시 서버로 전송
            firebaseTokenService.uploadToken(token).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        val res = response.body()
                        Log.d(TAG, "onResponse: $res")
                    } else {
                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, t.message ?: "토큰 정보 등록 중 통신오류")
                }
            })
        }
    }
}