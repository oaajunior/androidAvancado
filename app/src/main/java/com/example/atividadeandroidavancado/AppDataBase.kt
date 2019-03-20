package com.example.atividadeandroidavancado

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(IntervaloAgua::class), version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun intervaloDao():IntervaloAguaDao

}