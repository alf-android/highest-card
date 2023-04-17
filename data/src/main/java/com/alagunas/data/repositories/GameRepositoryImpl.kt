package com.alagunas.data.repositories

import com.alagunas.data.datasource.local.GameLocalDatasource

class GameRepositoryImpl(private val gameLocalDatasource: GameLocalDatasource) : GameRepository {

}