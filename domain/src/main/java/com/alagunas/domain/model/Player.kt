package com.alagunas.domain.model

data class Player(
    private var pile: List<Card>,
    val wastePile: MutableList<Card> = mutableListOf(),
    val wins: Int = 0,
    val winner: Boolean = false
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