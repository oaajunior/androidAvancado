package com.example.atividadeandroidavancado

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import java.time.LocalTime
import java.util.*

object AlarmUtil {

    var pendingIntent:PendingIntent? = null
    var alarm:AlarmManager? = null
    var retornoSucesso = false
    val horaFim:Calendar = Calendar.getInstance()
    val horaInicial:Calendar = Calendar.getInstance()

    fun setAlarm(context: Context, horaIntervalo: LocalTime, horaAcordar: LocalTime, horaDormir: LocalTime) :Boolean{

        horaInicial.set(Calendar.HOUR_OF_DAY, horaAcordar.hour)
        horaInicial.set(Calendar.MINUTE, horaAcordar.minute)

        horaFim.set(Calendar.HOUR_OF_DAY, horaDormir.hour)
        horaFim.set(Calendar.MINUTE, horaDormir.minute)

        Log.d("HORA_EM_MINUTO", horaIntervalo.minute.toLong().toString() )
        Log.d("HORA_EM_INICIO", horaInicial.toString() )
        Log.d("HORA_EM_MINUTO", horaFim.toString() )

        alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarm?.setRepeating(AlarmManager.RTC_WAKEUP, horaInicial.timeInMillis, 1000 * 60 * horaIntervalo.minute.toLong(), pendingIntent)

        retornoSucesso =  true

        return retornoSucesso
    }
}