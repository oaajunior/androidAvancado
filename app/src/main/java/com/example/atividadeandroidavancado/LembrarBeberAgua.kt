package com.example.atividadeandroidavancado

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//Activity para informar ao usuário que a hora de beber agua chegou
class LembrarBeberAgua : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lembrar_beber_agua)

        val btnOK = findViewById<Button>(R.id.btnOK)

        //Finaliza a activity
        btnOK.setOnClickListener {

            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
