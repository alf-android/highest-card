package com.alagunas.data.datamanager.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDAO {
    @Query("SELECT * FROM games")
    suspend fun getAll(): List<GameEntity>

    @Query("SELECT * FROM games WHERE id == :id LIMIT 1")
    suspend fun getById(id: String): GameEntity

    @Insert
    suspend fun insert(game: GameEntity)

    @Delete
    suspend fun delete(game: GameEntity)

    @Query("DELETE FROM games")
    suspend fun deleteAll()
}