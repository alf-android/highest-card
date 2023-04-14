package com.alagunas.usecases.game

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardSuit

class GetRoundWinnerUseCase {

    operator fun invoke(orderSuits: List<CardSuit>, dealedCards: Pair<Card, Card>): Card? {
        return when {
            dealedCards.first.faceName.value == dealedCards.second.faceName.value -> {
                orderSuits.find { it == dealedCards.first.suit }?.let { dealedCards.first }
                orderSuits.find { it == dealedCards.second.suit }?.let { dealedCards.second }
            }
            dealedCards.first.faceName.value > dealedCards.second.faceName.value -> dealedCards.first
            dealedCards.first.faceName.value < dealedCards.second.faceName.value -> dealedCards.second
            else -> null
        }
    }
}