package com.alagunas.usecases.game

import com.alagunas.domain.model.Player

class WinRoundUseCase {

    operator fun invoke(player: Player): Player =
        player.copy(wins = player.wins + 1, discardPile = player.discardPile + 2)
}