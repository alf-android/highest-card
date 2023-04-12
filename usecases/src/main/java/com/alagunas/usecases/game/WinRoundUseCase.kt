package com.alagunas.usecases.game

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WinRoundUseCase {

    operator fun invoke(player: Player, winnedCards: List<Card>): Flow<Boolean> = flow {
        player.wins++
        player.winnerPile.addAll(winnedCards)
        player.winner = true
        emit(player.winner)
    }
}