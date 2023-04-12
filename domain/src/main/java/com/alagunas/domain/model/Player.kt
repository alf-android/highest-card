package com.alagunas.domain.model

data class Player(
    var pile: List<Card>,
    val winnerPile: MutableList<Card> = mutableListOf(),
    var wins: Int = 0,
    var winner: Boolean = false
) {

    fun getPileSize(): Int {
        return pile.size
    }

    fun dealTop(): Card {
        val dealedCard = pile.last()
        pile = pile.take(pile.size - 1)
        return dealedCard
    }
}