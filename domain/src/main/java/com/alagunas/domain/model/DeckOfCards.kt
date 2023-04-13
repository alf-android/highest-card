package com.alagunas.domain.model

class DeckOfCards {
    private val suits = CardSuit.values()
    private val faceNames = CardFaceName.values()
    val deck: MutableList<Card> = mutableListOf()

    init {
        suits.forEach { suit ->
            faceNames.forEach { faceName ->
                deck.add(Card(faceName, suit))
            }
        }
        deck.shuffle()
    }
}