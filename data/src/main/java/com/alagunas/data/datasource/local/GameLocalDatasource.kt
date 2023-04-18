package com.alagunas.data.datasource.local

import com.alagunas.domain.model.Game

interface GameLocalDatasource {
    suspend fun getGames(): List<Game>
    suspend fun deleteGames()
}