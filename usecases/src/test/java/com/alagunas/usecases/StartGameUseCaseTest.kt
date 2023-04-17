package com.alagunas.usecases

import com.alagunas.usecases.game.StartGameUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StartGameUseCaseTest {

    private lateinit var startGameUseCase: StartGameUseCase

    @Before
    fun setUp() {
        startGameUseCase = StartGameUseCase()
    }

    @Test
    fun shouldHaveSameCardsEachPlayer() {
        val players = startGameUseCase(Unit)
        assertEquals(2, players.size)
        assertEquals(26, players[0].pile.size)
        assertEquals(0, players[0].discardPile)
        assertEquals(0, players[0].wins)
        assertEquals(26, players[1].pile.size)
        assertEquals(0, players[1].discardPile)
        assertEquals(0, players[1].wins)
    }
}