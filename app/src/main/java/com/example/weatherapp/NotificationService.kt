package com.example.weatherapp

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder

class NotificationService: Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val title = intent?.getStringExtra("Temperature") ?: ""
        val content = intent?.getStringExtra("Location") ?: ""

        val runner = Runnable {
            val notifier = Notifier(this)
            notifier.sendNotification(title,content)
        }

        return START_STICKY
    }
}