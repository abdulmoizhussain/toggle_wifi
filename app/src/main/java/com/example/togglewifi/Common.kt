package com.example.togglewifi

import android.content.Context
import android.net.Uri
import android.net.wifi.WifiManager

object Common {
    const val AppNotificationId = 100
    private const val MsgPrefix = "MsgPrefix:"
    fun printLn(msg: String) = println("$MsgPrefix $msg")
    fun printLn(msg: Boolean) = println("$MsgPrefix $msg")
    fun printLn(msg: Int) = println("$MsgPrefix $msg")
    fun printLn(msg: Double) = println("$MsgPrefix $msg")
    fun printLn(msg: Float) = println("$MsgPrefix $msg")
    fun printLn(msg: Long) = println("$MsgPrefix $msg")

    fun getIntentData(purpose: String): Uri = Uri.parse(purpose)

    fun toggleWifiState(context: Context) {
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        @Suppress("DEPRECATION")
        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
    }

    fun isWifiEnabled(context: Context): Boolean {
        return (context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager).isWifiEnabled
    }

    const val NotificationBuilderChannelId = "NotificationBuilderChannelId"
}

object IntentPurpose {
    const val TOGGLE_WIFI = "TOGGLE_WIFI"
    const val TOGGLE_TORCH = "TOGGLE_TORCH"
    const val CHANGE_ORIENTATION = "CHANGE_ORIENTATION"
}

object RequestCode {
    const val ACTION_MANAGE_WRITE_SETTINGS = 100
    const val PENDING_INTENT_SERVICE_TOGGLE_WIFI = 101
    const val PENDING_INTENT_SERVICE_TOGGLE_TORCH = 102
    const val PENDING_INTENT_SERVICE_CHANGE_ORIENTATION = 103
}