package com.alagunas.data.repositories

import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player

interface GameRepository {
    suspend fun setGame(players: Pair<Player, Player>)
    suspend fun getGames(): List<Game>
    suspend fun deleteGames()
}