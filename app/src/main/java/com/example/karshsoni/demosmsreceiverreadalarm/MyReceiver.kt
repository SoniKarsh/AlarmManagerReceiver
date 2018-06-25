package com.example.karshsoni.demosmsreceiverreadalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Toast
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.Context.ALARM_SERVICE


class MyReceiver : BroadcastReceiver(){
    lateinit var messageText: String
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmMgr: AlarmManager
        val alarmIntent: PendingIntent
        val extras = intent!!.extras
        val msgString = "set an alarm"
        if(extras!=null){
            val sms = extras.get("pdus") as Array<*>

            for(i in sms.indices){
                val format = extras.get("format")
                var smsMessage = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    SmsMessage.createFromPdu(sms[i] as ByteArray, format as String)
                }else{
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }
                val phoneNumber = smsMessage.originatingAddress
                messageText = smsMessage.messageBody.toString()


                Toast.makeText(context, "+"+messageText, Toast.LENGTH_LONG).show()

            }

        }
        if(messageText == msgString){
            setAlarm(10000, context)
//            val intentX = Intent(context, AlarmReceiver::class.java)
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intentX, 0)
//            alarmMgr = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,
//                    AlarmManager.INTERVAL_HALF_HOUR, alarmIntent)

        }

    }

    fun setAlarm(time: Long, context: Context?) {
        //getting the alarm manager
        val am = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //creating a new intent specifying the broadcast receiver
        val i = Intent(context, MyAlarm::class.java)

        //creating a pending intent using the intent
        val pi = PendingIntent.getBroadcast(context, 0, i, 0)

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, time, 6000000, pi)
        Toast.makeText(context, "Alarm is set", Toast.LENGTH_SHORT).show()
    }


}