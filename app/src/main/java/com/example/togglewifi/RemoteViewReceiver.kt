package com.example.togglewifi

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class RemoteViewReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

//        Common.printLn(intent?.action.toString())
//        Common.toggleWifiState(this)
//        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        wifiManager.isWifiEnabled = !wifiManager.isWifiEnabled
//        val a = wifiManager.wifiState
//        WifiManager.WIFI_STATE_ENABLED
//        WifiManager.WIFI_STATE_UNKNOWN
//        WifiManager.WIFI_STATE_DISABLED
//        WifiManager.WIFI_STATE_DISABLING
        val intentData = intent.data.toString()
        Common.printLn(intentData)

        NotificationHelper.create(context)

        when (intentData) {
            IntentPurpose.CHANGE_ORIENTATION -> {
            }
            IntentPurpose.TOGGLE_WIFI -> {
                if (ifWriteSettingsIsNotAllowed(context)) {
                    return
                }
                Common.toggleWifiState(context)
            }
            IntentPurpose.TOGGLE_TORCH -> {
            }
        }
    }

    private fun ifWriteSettingsIsNotAllowed(context: Context): Boolean {
        val permission: Boolean

        fun showToast() = Toast.makeText(
            context,
            "Plz allow me to modify system settings, so I can change your phone's orientation.",
            Toast.LENGTH_LONG
        ).show()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = Settings.System.canWrite(context)
            if (!permission) {
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:" + context.packageName)
                showToast()
//                context.startActivityForResult(intent, RequestCode.ACTION_MANAGE_WRITE_SETTINGS)
                context.startActivity(intent)
            }
        } else {
            permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_SETTINGS) == PackageManager.PERMISSION_GRANTED
            showToast()
//            if (!permission) {
//                ActivityCompat.requestPermissions(
//                    context as Activity, arrayOf(Manifest.permission.WRITE_SETTINGS),
//                    RequestCode.ACTION_MANAGE_WRITE_SETTINGS
//                )
//            }
        }
        return !permission
    }
}
