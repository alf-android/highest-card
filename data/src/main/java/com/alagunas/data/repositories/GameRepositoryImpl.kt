package com.alagunas.data.repositories

import com.alagunas.data.datasource.local.GameLocalDatasource
import com.alagunas.domain.model.Game

class GameRepositoryImpl(private val gameLocalDatasource: GameLocalDatasource) : GameRepository {
    override suspend fun getGames(): List<Game> {
        return gameLocalDatasource.getGames()
    }

    override suspend fun deleteGames() {
        gameLocalDatasource.deleteGames()
    }

}