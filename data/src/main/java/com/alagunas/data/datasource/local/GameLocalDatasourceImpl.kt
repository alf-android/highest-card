package com.alagunas.data.datasource.local

import com.alagunas.data.datamanager.db.GameDAO
import com.alagunas.data.datamanager.db.toGame
import com.alagunas.domain.model.Game

class GameLocalDatasourceImpl(private val gameDAO: GameDAO) : GameLocalDatasource {
    override suspend fun getGames(): List<Game> {
        return gameDAO.getAll().map { it.toGame() }
    }

    override suspend fun deleteGames() {
        return gameDAO.deleteAll()
    }
}