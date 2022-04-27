package com.example.weatherapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.weatherapp.R.*


class Notifier(val context: Context) {
    init {
        createNotificationChannel()
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = context.getString(R.string.channelName)
            val channelDescription = context.getString(string.channelDescription)
            val channelId = channelName
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
               context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun notification(title: String, content: String) : Notification {

        val intent = Intent(context, ViewActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        intent.putExtra("title",title)

        val requestCode = title.hashCode()
        val pendingIntent = PendingIntent.getActivity(context,requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, context.getString(R.string.channelName))
            builder.setSmallIcon(R.drawable.notifications_icon)
            builder.setContentTitle(title)
            builder.setContentText(content)
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()

    }
    fun sendNotification(temperature: String, location: String) {
        val notification = notification(temperature, location)
        with(NotificationManagerCompat.from(context)) {
            val notificationId = temperature.hashCode()

            notfy(notificationId, notification)
        }
    }

    private fun notfy(notificationId: Int, notification: Notification) {

    }


}

