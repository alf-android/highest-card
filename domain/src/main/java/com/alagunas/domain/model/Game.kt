package com.alagunas.domain.model

import java.util.*

data class Game (
    val id: String = UUID.randomUUID().toString(),
    val players: List<Player> = listOf()
)