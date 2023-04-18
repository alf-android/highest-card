package com.alagunas.data.datamanager.db.core

import com.alagunas.data.datamanager.db.GameDAO
import com.alagunas.data.datamanager.db.GameDatabase

fun provideDao(dataBase: GameDatabase): GameDAO {
    return dataBase.gameDAO
}