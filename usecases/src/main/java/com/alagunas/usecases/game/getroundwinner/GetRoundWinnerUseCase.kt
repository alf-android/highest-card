package com.alagunas.usecases.game.getroundwinner

import com.alagunas.domain.model.Card
import com.alagunas.usecases.core.UseCase

class GetRoundWinnerUseCase : UseCase<BodyGetRoundWinner, Card?> {

    override operator fun invoke(params: BodyGetRoundWinner): Card? {
        return when {
            params.dealedCards.first.faceName.value == params.dealedCards.second.faceName.value -> {
                params.orderSuits.forEach {
                    if (params.dealedCards.first.suit == it) return params.dealedCards.first
                    if (params.dealedCards.second.suit == it) return params.dealedCards.second
                }
                return null
            }
            params.dealedCards.first.faceName.value > params.dealedCards.second.faceName.value -> params.dealedCards.first
            params.dealedCards.first.faceName.value < params.dealedCards.second.faceName.value -> params.dealedCards.second
            else -> null
        }
    }
}