package com.alagunas.usecases

import com.alagunas.domain.model.Player
import com.alagunas.usecases.game.WinRoundUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WinRoundUseCaseTest {

    private lateinit var winRoundUseCase: WinRoundUseCase

    @Before
    fun setUp() {
        winRoundUseCase = WinRoundUseCase()
    }

    @Test
    fun shouldAddOneMoreWinAndTwoMoreCardsInDiscardPile() {
        val winner = winRoundUseCase(Player(wins = 2, discardPile = 14))
        assertEquals(3, winner.wins)
        assertEquals(16, winner.discardPile)
    }
}