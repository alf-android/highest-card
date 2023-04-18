package com.alagunas.data.datamanager.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "players") val players: List<Player>?
)

fun GameEntity.toGame() =
    Game(
        id = id,
        players = players ?: listOf()
    )