package com.alagunas.usecases

import com.alagunas.domain.model.CardSuit
import com.alagunas.usecases.game.GetSuitOrderUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetSuitOrderUseCaseTest {

    private lateinit var getSuitOrderUseCase: GetSuitOrderUseCase

    @Before
    fun setUp() {
        getSuitOrderUseCase = GetSuitOrderUseCase()
    }

    @Test
    fun shouldHaveFourSuits() {
        val order = getSuitOrderUseCase(Unit)
        assertEquals(4, order.size)
    }

    @Test
    fun containsEachSuitInTheList() {
        val order = getSuitOrderUseCase(Unit)
        assertEquals(true, order.contains(CardSuit.CLUBS))
        assertEquals(true, order.contains(CardSuit.HEARTS))
        assertEquals(true, order.contains(CardSuit.SPADES))
        assertEquals(true, order.contains(CardSuit.DIAMONDS))
    }
}