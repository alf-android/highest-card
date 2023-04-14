package com.alagunas.domain.model

data class Player(
    val pile: List<Card> = listOf(),
    val discardPile: Int = 0,
    val wins: Int = 0
)