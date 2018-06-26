package com.example.karshsoni.demosmsreceiverreadalarm

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log


internal class MyAlarm : BroadcastReceiver() {
    lateinit var notificationManager:NotificationManager
    lateinit var notificationChannel:NotificationChannel
    lateinit var builder:Notification.Builder
    private val channelId= "com.example.karshsoni.demosmsreceiverreadalarm"
    private val text="test"
   
    override fun onReceive(context: Context, intent: Intent) {

        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d("MyAlarmBelal", "Alarm just fired")

        val notificationIntent = Intent(context, Main2Activity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)


        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationChannel = NotificationChannel(channelId, text, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.BLUE
        notificationChannel.enableVibration(false)
        notificationManager.createNotificationChannel(notificationChannel)

        builder = Notification.Builder(context, channelId)
                .setContentTitle("Alarm Ringing")
                .setContentText("Ringing")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.notification_icon_background))
                .setContentIntent(pendingIntent)

        notificationManager.notify(123, builder.build())

    }



}
