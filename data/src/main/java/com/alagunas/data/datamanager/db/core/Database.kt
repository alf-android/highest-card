package com.alagunas.data.datamanager.db.core

import android.app.Application
import androidx.room.Room
import com.alagunas.data.datamanager.db.GameDatabase

fun buildDataBase(application: Application): GameDatabase =
    Room.databaseBuilder(application, GameDatabase::class.java, "GameDB")
        .fallbackToDestructiveMigration()
        .build()