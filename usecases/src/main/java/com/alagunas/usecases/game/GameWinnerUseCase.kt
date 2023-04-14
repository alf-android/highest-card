package com.alagunas.usecases.game

import com.alagunas.domain.model.Player
import com.alagunas.usecases.core.UseCase

class GameWinnerUseCase: UseCase<Pair<Player, Player>, Player?> {
    override fun invoke(players: Pair<Player, Player>): Player? {
        return when {
            (players.first.wins > players.second.wins) -> players.first
            (players.first.wins < players.second.wins) -> players.second
            else -> null
        }
    }
}