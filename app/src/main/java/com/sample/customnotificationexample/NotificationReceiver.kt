package com.sample.customnotificationexample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Image clicked!", Toast.LENGTH_SHORT).show()

        // Removing the notification
        val notificationManager = NotificationManagerCompat.from(context!!)
        notificationManager.cancel(1)
    }
}