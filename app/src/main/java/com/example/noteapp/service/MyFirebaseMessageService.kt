package com.example.noteapp.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.noteapp.R
import com.example.noteapp.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessageService : FirebaseMessagingService() {

    companion object {

        const val CHANNEL_ID = "notification channel"

        const val CHANNEL_NAME = "Notification Channel"

        const val NOTIFICATION_id = 0

    }

    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let { notification ->

            val title = notification.title ?: ""
            val body = notification.body ?: ""

            showNotification(title, body)

        }


    }


    @SuppressLint("MissingPermission")
    private fun showNotification(title: String, body: String) {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        }
        val pendingInten = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationLayout = getNotificationLayout(title, body)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.img_notification)
            .setContentIntent(pendingInten)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)

        creatNotificationChanell()
        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_id, builder.build())
        }


    }

    private fun getNotificationLayout(title: String, body: String): RemoteViews? {

        val remoteViews = RemoteViews(packageName, R.layout.item_notification)
        remoteViews.setTextViewText(R.id.tv_title, title)
        remoteViews.setTextViewText(R.id.tv_description, body)
        remoteViews.setImageViewResource(R.id.img_logo, R.drawable.img_notification)
        return remoteViews

    }

    private fun creatNotificationChanell() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)


        }

    }


}