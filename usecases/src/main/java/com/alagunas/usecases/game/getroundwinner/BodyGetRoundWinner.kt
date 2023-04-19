package com.alagunas.usecases.game.getroundwinner

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardSuit

data class BodyGetRoundWinner (
    val orderSuits: List<CardSuit>,
    val dealedCards: Pair<Card, Card>
)