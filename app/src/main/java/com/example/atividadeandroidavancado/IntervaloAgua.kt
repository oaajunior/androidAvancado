package com.example.atividadeandroidavancado

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class IntervaloAgua (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val horaIntervalo: String,
    val horaAcordar: String,
    val horaDormir: String
)