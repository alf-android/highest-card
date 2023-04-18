package com.alagunas.data.repositories

import com.alagunas.domain.model.Game

interface GameRepository {
    suspend fun getGames(): List<Game>
    suspend fun deleteGames()
}