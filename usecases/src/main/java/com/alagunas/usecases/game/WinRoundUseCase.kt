package com.alagunas.usecases.game

import com.alagunas.domain.model.Player
import com.alagunas.usecases.core.UseCase

class WinRoundUseCase: UseCase<Player, Player> {

    override operator fun invoke(player: Player): Player =
        player.copy(wins = player.wins + 1, discardPile = player.discardPile + 2)
}