package com.example.atividadeandroidavancado

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import java.util.*

//Classe para receber o BroadcastReceiver do sistema que o horário
class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val horaAtual = Calendar.getInstance()

        Log.d("ALARMRECEIVERATUAL", horaAtual.timeInMillis.toString() )
        Log.d("ALARMRECEIVERINICIAL", AlarmUtil.horaInicial.timeInMillis.toString() )
        Log.d("ALARMRECEIVERFIM", AlarmUtil.horaFim.timeInMillis.toString() )


        if( horaAtual.timeInMillis < AlarmUtil.horaFim.timeInMillis  &&
            horaAtual.timeInMillis  > AlarmUtil.horaInicial.timeInMillis)
            setNotification(context)
    }

    private fun setNotification(context: Context?) {

        val manager =  createChannel(context)
        val intent = Intent(context, LembrarBeberAgua::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(context!!.applicationContext, "beber agua")
            .setContentIntent(pendingIntent)
            .setContentTitle("Bora se hidratar?")
            .setContentText("Está na hora de beber água!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .build()

             manager.notify(12, notification)
    }

    private fun createChannel(context: Context?) : NotificationManager {

        val channel = NotificationChannel("beber agua", "Bora se Hidratar?", NotificationManager.IMPORTANCE_HIGH)
        val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        return manager
    }
}