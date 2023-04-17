package com.alagunas.usecases.game

import com.alagunas.domain.model.CardSuit
import com.alagunas.usecases.core.UseCase

class GetSuitOrderUseCase : UseCase<Unit, List<CardSuit>> {
    override fun invoke(params: Unit): List<CardSuit> {
        val suits = CardSuit.values()
        suits.shuffle()
        return suits.toList()
    }
}