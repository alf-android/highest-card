package com.alagunas.usecases.game

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.Player
import com.alagunas.usecases.core.UseCase

class DealTopUseCase: UseCase<Player, Pair<Player, Card>> {

    override fun invoke(player: Player): Pair<Player, Card> {
        val dealedCard = player.pile.last()
        val playerUpdated = player.copy(pile = player.pile.take(player.pile.size - 1))
        return Pair(playerUpdated, dealedCard)
    }
}