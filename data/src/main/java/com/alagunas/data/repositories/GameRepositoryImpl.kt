package com.alagunas.data.repositories

import com.alagunas.data.datasource.local.GameLocalDatasource
import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player

class GameRepositoryImpl(private val gameLocalDatasource: GameLocalDatasource) : GameRepository {
    override suspend fun setGame(players: Pair<Player, Player>) {
        gameLocalDatasource.setGame(players)
    }

    override suspend fun getGames(): List<Game> {
        return gameLocalDatasource.getGames()
    }

    override suspend fun deleteGames() {
        gameLocalDatasource.deleteGames()
    }

}