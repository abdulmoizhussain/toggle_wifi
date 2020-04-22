package com.example.togglewifi

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.view.Surface
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationHelper.create(this)

//        val validConfig = Configuration()
//        validConfig.setToDefaults()
//
//        val deltaOnlyConfig = Configuration(this.resources.configuration)
//        deltaOnlyConfig.orientation = Configuration.ORIENTATION_LANDSCAPE
//        validConfig.updateFrom(deltaOnlyConfig)

//        this.resources.updateConfiguration(deltaOnlyConfig, this.resources.displayMetrics)
//
//        this.baseContext.resources.updateConfiguration(
//            deltaOnlyConfig,
//            this.baseContext.resources.displayMetrics
//        )

//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        val asdf = Configuration()
//        asdf.orientation = Configuration.ORIENTATION_LANDSCAPE

//        Configuration().updateFrom(asdf)
    }

    private fun changeAndroidOrientation() {
        val v1 = Settings.System.putInt(
            contentResolver,
            Settings.System.ACCELEROMETER_ROTATION,
            0 // 0 means off, 1 means on
        )

        val v2 = Settings.System.putInt(
            contentResolver,
            Settings.System.USER_ROTATION,
            Surface.ROTATION_90 // 0 means off, 1 means on
        )
        println(v1)
        println(v2)
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
    private fun notification() {
        val intent = Intent(applicationContext, RemoteViewReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val mBuilder = NotificationCompat.Builder(applicationContext, "channelId")
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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            RequestCode.ACTION_MANAGE_WRITE_SETTINGS -> {
//                Common.printLn("result came $resultCode, $requestCode, ${data.toString()}")
//            }
//        }
//    }
}

object NotificationHelper {
    fun create(context: Context) {
        val applicationContext = context.applicationContext

        val mBuilder = NotificationCompat.Builder(applicationContext, Common.NotificationBuilderChannelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("Tap to toggle wifi state")
//            .setWhen(System.currentTimeMillis())
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setShowWhen(false)
            .setOngoing(true)
            .setTimeoutAfter(Long.MAX_VALUE)
            .setAutoCancel(false)
//            .setNumber(100)
//            .addAction(R.drawable.ic_launcher_background, "My Action", yepPendingIntent)

        // Bingo
        val view = RemoteViews(context.packageName, R.layout.custom_notification_small)

        view.setTextViewText(R.id.toggle_wifi, if (Common.isWifiEnabled(context)) "off wifi" else "on wifi")

        view.setOnClickPendingIntent(R.id.change_phone_orientation, getPendingIntentFor(applicationContext, IntentPurpose.CHANGE_ORIENTATION))

        view.setOnClickPendingIntent(R.id.toggle_wifi, getPendingIntentFor(applicationContext, IntentPurpose.TOGGLE_WIFI))

        view.setOnClickPendingIntent(R.id.toggle_torch, getPendingIntentFor(applicationContext, IntentPurpose.TOGGLE_TORCH))

//        mBuilder.setContent(view)
        mBuilder.setCustomContentView(view)
//        mBuilder.setCustomBigContentView(view)

        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(Common.AppNotificationId, mBuilder.build())
    }

    private fun getPendingIntentFor(applicationContext: Context, action: String): PendingIntent {
        val intent = Intent(applicationContext, RemoteViewReceiver::class.java)
        intent.data = Common.getIntentData(action)
        return PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
    }
}