package com.alagunas.domain.model

data class Player(
    var pile: List<Card>,
    val winnerPile: MutableList<Card> = mutableListOf(),
    var wins: Int = 0,
    var winner: Boolean = false
)