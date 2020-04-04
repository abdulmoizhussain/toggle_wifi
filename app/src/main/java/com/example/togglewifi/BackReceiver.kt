package com.example.togglewifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager

class BackReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        @Suppress("DEPRECATION")
        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
    }
}
