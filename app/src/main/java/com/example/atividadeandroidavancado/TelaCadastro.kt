package com.example.atividadeandroidavancado

import android.app.AlarmManager
import android.app.PendingIntent
import android.arch.persistence.room.Room
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.app.Activity



class TelaCadastro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_tela)

        val botaoCadastrarIntervalo = findViewById<Button>(R.id.btnCadastrarIntervalo)

        val database = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "armazenar_intervalo")
            .allowMainThreadQueries()
            .build()

        botaoCadastrarIntervalo.setOnClickListener {

            var horaUm = findViewById<TextView>(R.id.editTxtHoraIntervalo).getText().toString()
            var horaDois = findViewById<TextView>(R.id.editTxtHoraAcordar).getText().toString()
            var horaTres = findViewById<TextView>(R.id.editTxtHoradormir).getText().toString()

            try {
                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                var horaIntervalo = LocalTime.parse(horaUm, formatter)
                var horaAcordar = LocalTime.parse(horaDois, formatter)
                var horaDormir = LocalTime.parse(horaTres, formatter)

                val intervaloAguaDao = database.intervaloDao()
                intervaloAguaDao.add(IntervaloAgua(0, horaIntervalo.toString(), horaAcordar.toString(), horaDormir.toString()))

                val resultado = intervaloAguaDao.all()
                for(item in resultado){
                    Log.d("Elementos" , "${item.id}, ${item.horaIntervalo}, ${item.horaAcordar}, ${item.horaDormir} ")
                }

                val retorno = AlarmUtil.setAlarm(this, horaIntervalo, horaAcordar, horaDormir)

                if(retorno) {
                    val data = Intent()
                    data.putExtra("next_activity_name_value", "Intervalo cadastrado com sucesso!")
                    setResult(Activity.RESULT_OK, data)
                    finish()
                }else{
                    val data = Intent()
                    data.putExtra("next_activity_name_value", "Houve erro ao cadastrar o intervalo!")
                    setResult(Activity.RESULT_CANCELED, data)
                    finish()
                }

            } catch (e: Exception){

                Toast.makeText(this,"Por favor, informe um intervalo de horas corretamente", Toast.LENGTH_LONG).show()
            }
        }
    }
}
