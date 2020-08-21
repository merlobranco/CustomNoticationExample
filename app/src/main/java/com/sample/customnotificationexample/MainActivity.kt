package com.sample.customnotificationexample

import android.app.PendingIntent
import android.content.Intent
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


        // Triggering the broadcast receiver
        val clickIntent = Intent(this, NotificationReceiver::class.java)
        // Remember a pending intent is an object we could pass to another process
        var clickPendingIntent = PendingIntent.getBroadcast(this, 0, clickIntent, 0)

        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World!")
        expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.otter)
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, clickPendingIntent)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setContentTitle("Title")
                .setContentText("This is a default notification")
                .build()

        notificationManager.notify(1, notification)
    }
}
