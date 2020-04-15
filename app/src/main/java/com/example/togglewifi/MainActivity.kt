package com.example.togglewifi

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        notification()


        try3()
    }

    private fun try0() {
        val mDevicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val answer = mDevicePolicyManager.isAdminActive(ComponentName(this, MainActivity::class.java))
//        mDevicePolicyManager.lockNow()

//        intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
//        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, ComponentName(this, DemoAdminReceiver::class.java))
//        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "aaaaaaaaa")
//        startActivityForResult(intent, 5)
    }

    private fun try2() {
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, "aasd")
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Additional text explaining why this needs to be added.")
        startActivityForResult(intent, 5)
//        startActivity(Intent().setComponent(ComponentName("com.android.settings", "com.android.settings.DeviceAdminSettings")))
//        Runtime.getRuntime().exec()

    }

//    @SuppressLint("InlinedApi")
    private fun try3() {
        val yepIntent = Intent(this, MyIntentService::class.java)
        val yepPendingIntent = PendingIntent.getService(this, 123, yepIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        val mBuilder = NotificationCompat.Builder(applicationContext, "channelId")
            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("Tap to toggle wifi state")
            .setWhen(System.currentTimeMillis())
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setShowWhen(false)
            .setOngoing(true)
            .setTimeoutAfter(Long.MAX_VALUE)
            .setAutoCancel(false)
//            .setNumber(100)
//            .addAction(R.drawable.ic_launcher_background, "My Action", yepPendingIntent)

        // Bingo
//        RemoteViews view = new RemoteViews(getPackageName(), R.layout.notification);
//        view.setOnClickPendingIntent(R.id.notification_closebtn_ib, yepPendingIntent);
//        builder.setContent(view);

        // Bingo
        val view = RemoteViews(packageName, R.layout.custom_notification_small)
        view.setOnClickPendingIntent(R.id.notif_wifi, yepPendingIntent)
//        mBuilder.setContent(view)
        mBuilder.setCustomBigContentView(view)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())
    }

//    @SuppressLint("InlinedApi")
    private fun notification() {
        val intent = Intent(this, BackReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val mBuilder = NotificationCompat.Builder(this, "channelId")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Tap to toggle wifi state")
            .setWhen(System.currentTimeMillis())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setShowWhen(false)
            .setOngoing(true)
            .setAutoCancel(false)
            .setNumber(100)
            .setContentIntent(pendingIntent)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())
    }
}
