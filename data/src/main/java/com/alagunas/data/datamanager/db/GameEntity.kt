package com.alagunas.data.datamanager.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player

private const val WINS_PLAYER_A = "WINS_PLAYER_A"
private const val WINS_PLAYER_B = "WINS_PLAYER_B"

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = WINS_PLAYER_A) val winsPlayerA: Int,
    @ColumnInfo(name = WINS_PLAYER_B) val winsPlayerB: Int
)

fun GameEntity.toGame() =
    Game(
        id = id,
        players = listOf(Player(wins = winsPlayerA), Player(wins = winsPlayerB))
    )