package com.alagunas.usecases.game

import com.alagunas.domain.model.DeckOfCards
import com.alagunas.domain.model.Player
import com.alagunas.usecases.core.UseCase

class StartGameUseCase: UseCase<Unit, List<Player>> {

    override fun invoke(params: Unit): List<Player> {
        val deckOfCards = DeckOfCards()
        val n = deckOfCards.deck.size
        val pile1 = deckOfCards.deck.subList(0, (n + 1) / 2)
        val pile2 = deckOfCards.deck.subList((n + 1) / 2, n)
        val playerA = Player(pile1)
        val playerB = Player(pile2)
        return listOf(playerA, playerB)
    }
}