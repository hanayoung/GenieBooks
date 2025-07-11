package com.ssafy.finalproject

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssafy.finalproject.base.ApplicationClass
import com.ssafy.finalproject.ui.MainActivity

private const val TAG = "MyFirebaseMsgSvc_싸피"

class MyFirebaseMessageService : FirebaseMessagingService() {
    // 새로운 토큰이 생성될 때 마다 해당 콜백이 호출된다.
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
//        MainActivity.uploadToken(token)
        ApplicationClass.sharedPreferencesUtil.setFcmToken(token)
    }

    // Foreground, Background 모두 처리하기 위해서는 data에 값을 담아서 넘긴다.
    //https://firebase.google.com/docs/cloud-messaging/android/receive
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var messageTitle = ""
        var messageContent = ""

        if (remoteMessage.notification != null) { // notification이 있는 경우 foreground처리
            //foreground
            messageTitle = remoteMessage.notification!!.title.toString()
            messageContent = remoteMessage.notification!!.body.toString()

            Log.d(TAG, "onMessageReceived: ${messageContent} ${messageTitle}")

        } else {  // background 에 있을경우 혹은 foreground에 있을경우 두 경우 모두
            val data = remoteMessage.data
            Log.d(TAG, "data.message: ${data}")
            Log.d(TAG, "data.message: ${data.get("myTitle")}")
            Log.d(TAG, "data.message: ${data.get("myBody")}")

            messageTitle = data.get("myTitle").toString()
            messageContent = data.get("myBody").toString()
        }

//        val mainIntent = Intent(this, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        val mainPendingIntent: PendingIntent =
//            PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE)
//
        val builder1 = NotificationCompat.Builder(this, MainActivity.channel_id)
            .setSmallIcon(R.drawable.logo_img_bg)
            .setContentTitle(messageTitle)
            .setContentText(messageContent)
            .setAutoCancel(true)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(101, builder1.build())

        ApplicationClass.sharedPreferencesUtil.addNotice(messageContent)
        Log.d(TAG, "onMessageReceived: ${messageContent}")
    }
}