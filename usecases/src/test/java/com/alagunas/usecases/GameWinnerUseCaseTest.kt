package com.alagunas.usecases

import com.alagunas.domain.model.Player
import com.alagunas.usecases.game.GameWinnerUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GameWinnerUseCaseTest {

    private lateinit var gameWinnerUseCase: GameWinnerUseCase

    @Before
    fun setUp() {
        gameWinnerUseCase = GameWinnerUseCase()
    }

    @Test
    fun playerWithMoreWinsIsTheWinner() {
        val playerWinner = Player(wins = 20)
        val playerLoser = Player(wins = 6)
        val playersInGame = gameWinnerUseCase(Pair(playerWinner, playerLoser))
        assertEquals(playerWinner, playersInGame)
        val playersInGameDifferentOrder = gameWinnerUseCase(Pair(playerLoser, playerWinner))
        assertEquals(playerWinner, playersInGameDifferentOrder)
    }

    @Test
    fun isDrawWhenAllPlayersHaveTheSameWins() {
        val playerDraw1 = Player(wins = 13)
        val playerDraw2 = Player(wins = 13)
        val playersInGame3 = gameWinnerUseCase(Pair(playerDraw1, playerDraw2))
        assertEquals(null, playersInGame3)
    }
}