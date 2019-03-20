package com.example.atividadeandroidavancado

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

        //Captura do botão que cadastra o intervalo para o usuário
        val botaoCadastrarIntervalo = findViewById<Button>(R.id.btnCadastrarIntervalo)

        //Criação de uma instancia para conexão com o banco de dados
        val database = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "armazenar_intervalo")
            .allowMainThreadQueries()
            .build()

        //Metodo que cadastro o intervalo do usuário.
        botaoCadastrarIntervalo.setOnClickListener {

            var horaUm = findViewById<TextView>(R.id.editTxtHoraIntervalo)
            var horaDois = findViewById<TextView>(R.id.editTxtHoraAcordar)
            var horaTres = findViewById<TextView>(R.id.editTxtHoradormir)

            try {

                val intervaloAguaDao = database.intervaloDao()
                val resultado:List<IntervaloAgua> = intervaloAguaDao.all()

                    for(item in resultado) {
                        Log.d(
                            "Elementos",
                            "${item.id}, ${item.horaIntervalo}, ${item.horaAcordar}, ${item.horaDormir} "
                        )
                    }

                val formatter = DateTimeFormatter.ofPattern("HH:mm")
                var horaIntervalo = LocalTime.parse(horaUm.text.toString(), formatter)
                var horaAcordar = LocalTime.parse(horaDois.text.toString(), formatter)
                var horaDormir = LocalTime.parse(horaTres.text.toString(), formatter)

                //Inclui o novo horario no banco de dados
                intervaloAguaDao.add(IntervaloAgua(0, horaIntervalo.toString(), horaAcordar.toString(), horaDormir.toString()))

                //Executa o método setAlarm que configura os horarios informados pelo usuário.
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
