package com.alagunas.domain.model

import java.util.*

data class Player(
    val id: String = UUID.randomUUID().toString(),
    val pile: List<Card> = listOf(),
    val discardPile: Int = 0,
    val wins: Int = 0
)