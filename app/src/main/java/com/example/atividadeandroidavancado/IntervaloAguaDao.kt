package com.example.atividadeandroidavancado


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface IntervaloAguaDao {

    @Query("SELECT * FROM IntervaloAgua")
    fun all(): List<IntervaloAgua>

    @Insert
    fun add(vararg intervalo: IntervaloAgua)
}