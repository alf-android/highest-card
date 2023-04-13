package com.alagunas.usecases.game

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.Player

class WinRoundUseCase {

    operator fun invoke(player: Player, winnedCards: List<Card>) {
        player.wins++
        player.winnerPile.addAll(winnedCards)
        player.winner = true
    }
}