package com.example.togglewifi

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent

class DemoAdminReceiver : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)

        Common.printLn("received")
    }
}