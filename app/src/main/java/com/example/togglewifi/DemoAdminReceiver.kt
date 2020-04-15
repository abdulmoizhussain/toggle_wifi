package com.example.togglewifi

import android.app.KeyguardManager
import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import androidx.core.content.ContextCompat.getSystemService

class DemoAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)

        Common.printLn("received")


        val km =context. getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        val kl = km.newKeyguardLock("MyKeyguardLock")
//        kl.disableKeyguard()

        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = pm.newWakeLock(
            PowerManager.FULL_WAKE_LOCK
                    or PowerManager.ACQUIRE_CAUSES_WAKEUP
                    or PowerManager.ON_AFTER_RELEASE, "app:MyWakeLock"
        )
        wakeLock.acquire(2000)
    }
}