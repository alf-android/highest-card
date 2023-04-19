package com.alagunas.data.datasource.local

import com.alagunas.data.datamanager.db.GameDAO
import com.alagunas.data.datamanager.db.GameEntity
import com.alagunas.data.datamanager.db.toGame
import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player
import java.util.*

class GameLocalDatasourceImpl(private val gameDAO: GameDAO) : GameLocalDatasource {
    override suspend fun setGame(players: Pair<Player, Player>) {
        gameDAO.insert(
            GameEntity(
                id = UUID.randomUUID().toString(),
                winsPlayerA = players.first.wins,
                winsPlayerB = players.second.wins
            )
        )
    }

    override suspend fun getGames(): List<Game> {
        return gameDAO.getAll().map { it.toGame() }
    }

    override suspend fun deleteGames() {
        return gameDAO.deleteAll()
    }
}