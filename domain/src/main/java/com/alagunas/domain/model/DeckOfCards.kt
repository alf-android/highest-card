package com.alagunas.domain.model

class DeckOfCards {
    private val suits = CardSuit.values()
    private val faceNames = CardFaceName.values()
    private val deck: MutableList<Card> = mutableListOf()
    val pile1: List<Card>
    val pile2: List<Card>

    init {
        suits.forEach { suit ->
            faceNames.forEach { faceName ->
                deck.add(Card(faceName, suit))
            }
        }
        deck.shuffle()
        val n = deck.size
        pile1 = deck.subList(0, (n + 1) / 2)
        pile2 = deck.subList((n + 1) / 2, n)
    }
}