package com.alagunas.data.datasource.local

import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player

interface GameLocalDatasource {
    suspend fun setGame(players: Pair<Player, Player>)
    suspend fun getGames(): List<Game>
    suspend fun deleteGames()
}