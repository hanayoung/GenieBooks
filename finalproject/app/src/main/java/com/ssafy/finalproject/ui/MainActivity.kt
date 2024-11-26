package com.ssafy.finalproject.ui

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ssafy.finalproject.R
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.base.BaseActivity
import com.ssafy.finalproject.data.remote.RetrofitUtil
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.firebaseTokenService
import com.ssafy.finalproject.data.remote.RetrofitUtil.Companion.giftCardService
import com.ssafy.finalproject.databinding.ActivityMainBinding
import com.ssafy.finalproject.ui.home.fragments.HomeFragmentDirections
import com.ssafy.finalproject.util.PermissionChecker
import kotlinx.coroutines.launch
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Identifier
import org.altbeacon.beacon.MonitorNotifier
import org.altbeacon.beacon.RangeNotifier
import org.altbeacon.beacon.Region
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity_싸피"

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    /** permission check **/
    private val checker = PermissionChecker(this)

    private val runtimePermissions = arrayOf(Manifest.permission.POST_NOTIFICATIONS)

    private val runtimePermissionsBeacon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_ADVERTISE,
            Manifest.permission.BLUETOOTH_CONNECT,
        )
    } else {
        arrayOf(
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN,
        )
    }
    /** permission check **/

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController
    private lateinit var nAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent
    private lateinit var filters: Array<IntentFilter>
    private var giftCardId = -1

    private lateinit var beaconManager: BeaconManager
    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var bluetoothAdapter: BluetoothAdapter

    private val region = Region(
        "estimote",
        listOf(
            Identifier.parse(BEACON_UUID),
            Identifier.parse(BEACON_MAJOR),
            Identifier.parse(BEACON_MINOR)
        ),
        BLUETOOTH_ADDRESS
    )

    private var eventPopUpAble = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        navController = navHostFragment.navController
        binding.bnv.setupWithNavController(navController)

        hideBottomNavigationView(navController)

        setNFCAdapter()
        registerObserver()
        initBeacon()
        checkPermissionBeacon()
        checkPermission()
    }

    override fun onResume() {
        super.onResume()
        nAdapter.enableForegroundDispatch(this, pIntent, filters, null)

    }

    override fun onPause() {
        super.onPause()
        nAdapter.disableForegroundDispatch(this)
    }

    private fun setNFCAdapter() {
        nAdapter = NfcAdapter.getDefaultAdapter(this)

        // Intent 를 처리할 activity. --> PendingIntent
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP // 내가 top에 있으면 재사용 --> onNewIntent
        }
        pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        val filter = IntentFilter()
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED)
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        filter.addDataType("text/*")

        filters = arrayOf(filter)
    }

    private fun initBeacon(){
        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"))
        bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
    }

    private fun checkPermissionBeacon() {
        if (!checker.checkPermission(this, runtimePermissionsBeacon)) {
            checker.setOnGrantedListener {
                //퍼미션 획득 성공일때
                if(eventPopUpAble) startScan()
            }
            checker.requestPermissionLauncher.launch(runtimePermissionsBeacon)
        } else { //이미 전체 권한이 있는 경우
            if(eventPopUpAble) startScan()
        }
    }

    private fun startScan() {
        if (!bluetoothAdapter.isEnabled) {
            requestEnableBLE()
        }
        beaconManager.addMonitorNotifier(monitorNotifier)
        beaconManager.startMonitoring(region)

        beaconManager.addRangeNotifier(rangeNotifier)
        beaconManager.startRangingBeacons(region)
    }

    private fun requestEnableBLE() {
        val callBLEEnableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        requestBLEActivity.launch(callBLEEnableIntent)
        Log.d(TAG, "requestEnableBLE: ")
    }

    private val requestBLEActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // 사용자의 블루투스 사용이 가능한지 확인
        if (bluetoothAdapter.isEnabled) {
            if(eventPopUpAble) startScan()
        }else{
            Log.d(TAG, "불가능: ")
        }
    }

    private var monitorNotifier: MonitorNotifier = object : MonitorNotifier {
        override fun didEnterRegion(region: Region) { //발견 함.
            Log.d(TAG, "I just saw an beacon for the first time!")
        }

        override fun didExitRegion(region: Region) { //발견 못함.
            Log.d(TAG, "I no longer see an beacon")
        }

        override fun didDetermineStateForRegion(state: Int, region: Region) { //상태변경
            Log.d(TAG, "I have just switched from seeing/not seeing beacons: $state")
        }
    }

    private var rangeNotifier: RangeNotifier = object : RangeNotifier {
        override fun didRangeBeaconsInRegion(beacons: MutableCollection<Beacon>?, r: Region?) {
            beacons?.run {
                if (isNotEmpty()) {
                    forEach { beacon ->
                        if(eventPopUpAble) {
                            if (beacon.distance <= BEACON_DISTANCE) {
                                eventPopUpAble = false
                                beaconManager.stopMonitoring(region)
                                beaconManager.stopRangingBeacons(region)
                                addAttendance()
                            } else {
                                eventPopUpAble = true
                            }
                        }
                        Log.d(
                            TAG,
                            "distance: " + beacon.distance + " id:" + beacon.id1 + "/" + beacon.id2 + "/" + beacon.id3
                        )
                    }
                }
                if (isEmpty()) {
//                    Log.d(TAG, "didRangeBeaconsInRegion: 비컨을 찾을 수 없습니다.")
                }
            }
        }
    }

    fun addAttendance() {
        lifecycleScope.launch {
            runCatching {
                val id = ApplicationClass.sharedPreferencesUtil.getId()
                RetrofitUtil.attendanceService.addAttendance(id)
            }.onSuccess {
                Log.d(TAG, "addAttendance: ${it}")
                if (it) {
                    showToast("출석체크에 성공하였습니다.")
                }
            }.onFailure {
                Log.d(TAG, "addAttendance: fail ${it.message}")
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ")
        parseData(intent)
    }

    private fun parseData(intent: Intent) {
        val action = intent.action
        Log.d(TAG, "getNFCData: $action")
        if (action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            val messages = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES,
                    NdefMessage::class.java
                )
            } else {
                intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES) as Array<NdefMessage>
            }

            messages?.forEach {
                val message = it as NdefMessage
                message.records.forEach {
                    val type = String(it.type)
                    val payload = String(it.payload)

                    Log.d(TAG, "getNFCData: $type")
                    Log.d(TAG, "getNFCData: $payload")

                    if (type == "T") {
                        giftCardId = String(it.payload, 3, it.payload.size - 3).toInt()
                        Log.d(TAG, "getNFCData - giftCardId: $giftCardId")
                        viewModel.receiveGiftCard(
                            ApplicationClass.sharedPreferencesUtil.getUserId(),
                            giftCardId
                        )
                    }
                }
            }
        }

    }

    private fun registerObserver() {
        viewModel.isReceiveSuccess.observe(this) {
            showToast("선물이 도착했어요!😊")
            getGiftCard(giftCardId)
        }
    }

    private fun getGiftCard(giftCardId: Int) {
        lifecycleScope.launch {
            runCatching {
                giftCardService.getGiftCardById(giftCardId)
            }.onSuccess {
                val action = HomeFragmentDirections.actionHomeFragmentToMyGiftCardFragment(it)
                navController.navigate(action)
            }.onFailure {
                Log.d(TAG, "getGiftCard: ${it.message}")
            }
        }
    }

    private fun hideBottomNavigationView(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnv.visibility = when (destination.id) {
                R.id.homeFragment -> View.VISIBLE
                R.id.categoryFragment -> View.VISIBLE
                R.id.myPageFragment -> View.VISIBLE
                else -> View.GONE
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
//        initFCM()
        createNotificationChannel(channel_id, "smart_store")
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
//        fun uploadToken(token: String) {
//            // 새로운 토큰 수신 시 서버로 전송
//            firebaseTokenService.uploadToken(token).enqueue(object : Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    if (response.isSuccessful) {
//                        val res = response.body()
//                        Log.d(TAG, "onResponse: $res")
//                    } else {
//                        Log.d(TAG, "onResponse: Error Code ${response.code()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.d(TAG, t.message ?: "토큰 정보 등록 중 통신오류")
//                }
//            })
//        }

        private const val BEACON_UUID = "e2c56db5-dffb-48d2-b060-d0f5a71096e0" // 우리반 모두 동일값
        private const val BEACON_MAJOR = "40011" // 우리반 모두 동일값
        private const val BEACON_MINOR = "43424" // 우리반 모두 동일값
        private const val BLUETOOTH_ADDRESS = "C3:00:00:1C:5E:7F"
        private const val BEACON_DISTANCE = 10.0 // 거리
    }
}