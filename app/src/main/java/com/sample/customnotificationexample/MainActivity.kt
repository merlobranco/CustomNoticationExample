package com.sample.customnotificationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = NotificationManagerCompat.from(this)
    }

    fun showNotification(v: View) {
        // The part that is displaying our notification is on another process,
        // and we cannot access directly to this process and make changes directly to it,
        // because this would be a security issue,
        // so we need to use a remote view in order to call our collapsed and expanded layouts
        val collapsedView = RemoteViews(packageName, R.layout.notification_collapsed)
        val expandedView = RemoteViews(packageName, R.layout.notification_expanded)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setContentTitle("Title")
                .setContentText("This is a default notification")
                .build()

        notificationManager.notify(1, notification)
    }
}
