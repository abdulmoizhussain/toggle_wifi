package com.example.togglewifi

import android.content.Context
import android.net.wifi.WifiManager

object Common {
    private const val MsgPrefix = "MsgPrefix:"
    fun printLn(msg: String) {
        println("$MsgPrefix $msg")
    }

    fun toggleWifiState(context: Context) {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        @Suppress("DEPRECATION")
        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
    }
}