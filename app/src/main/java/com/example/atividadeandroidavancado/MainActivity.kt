package com.example.atividadeandroidavancado

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val NEXT_ACTIVITY_RESULT = 1
    val NEXT_ACTIVITY_NAME_VALUE = "next_activity_name_value"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.title = "Bora se hidratar?"

        val btnCadastrarIntervalo = findViewById<Button>(R.id.btnCadastrarIntervalo)
        val btnCancelarIntervalo = findViewById<Button>(R.id.btnCancelarIntervalo)
        val btnFecharApp = findViewById<Button>(R.id.btnFechar)

        btnCadastrarIntervalo.setOnClickListener {

            val intent = Intent(this, TelaCadastro::class.java)
            startActivityForResult(intent, NEXT_ACTIVITY_RESULT)
        }

        btnCancelarIntervalo.setOnClickListener {
            val alarme = AlarmUtil.alarm
            alarme?.cancel(AlarmUtil.pendingIntent)
            Log.d("OBERDAN", "AlarmeCancelado")
        }

        btnFecharApp.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode :Int, resultCode :Int, data :Intent? ) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == NEXT_ACTIVITY_RESULT) {

            Toast.makeText(this, data?.getStringExtra(NEXT_ACTIVITY_NAME_VALUE), Toast.LENGTH_LONG).show()

            }

        }else{

            Toast.makeText(this, data?.getStringExtra(NEXT_ACTIVITY_NAME_VALUE), Toast.LENGTH_LONG).show()
        }

     }




   /* override fun onDestroy() {
        super.onDestroy()
        val alarme = AlarmUtil.alarm
        alarme?.cancel(AlarmUtil.pendingIntent)
        Log.d("OBERDAN", "AlarmeCancelado")
    }*/
}
