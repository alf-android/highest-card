package com.alagunas.usecases

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardFaceName
import com.alagunas.domain.model.CardSuit
import com.alagunas.domain.model.Player
import com.alagunas.usecases.game.DealTopUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DealTopUseCaseTest {

    private lateinit var dealTopUseCase: DealTopUseCase

    @Before
    fun setUp() {
        dealTopUseCase = DealTopUseCase()
    }

    @Test
    fun playersShouldHaveOneCardLessInPile() {
        val playerA = Player(pile = listOf(
            Card(CardFaceName.TWO, CardSuit.DIAMONDS),
            Card(CardFaceName.THREE, CardSuit.DIAMONDS),
            Card(CardFaceName.FOUR, CardSuit.DIAMONDS),
            Card(CardFaceName.FIVE, CardSuit.DIAMONDS)
        ))
        val playerB = Player(pile = listOf(
            Card(CardFaceName.SIX, CardSuit.HEARTS),
            Card(CardFaceName.SEVEN, CardSuit.HEARTS),
            Card(CardFaceName.EIGHT, CardSuit.HEARTS),
            Card(CardFaceName.NINE, CardSuit.HEARTS)
        ))
        assertEquals(4, playerA.pile.size)
        assertEquals(4, playerB.pile.size)
        val playerAAndDealedCard = dealTopUseCase(playerA)
        val playerBAndDealedCard = dealTopUseCase(playerB)
        assertEquals(3, playerAAndDealedCard.first.pile.size)
        assertEquals(3, playerBAndDealedCard.first.pile.size)
        assertEquals(Card(CardFaceName.FIVE, CardSuit.DIAMONDS), playerAAndDealedCard.second)
        assertEquals(Card(CardFaceName.NINE, CardSuit.HEARTS), playerBAndDealedCard.second)
    }

    @Test
    fun isImposibleToDealTopWhenThereIsNoCardsInPile() {
        val playerA = Player()
        val playerB = Player()
        assertEquals(0, playerA.pile.size)
        assertEquals(0, playerB.pile.size)
        val playerAAndDealedCard = dealTopUseCase(playerA)
        val playerBAndDealedCard = dealTopUseCase(playerB)
        assertEquals(0, playerAAndDealedCard.first.pile.size)
        assertEquals(0, playerBAndDealedCard.first.pile.size)
        assertEquals(null, playerAAndDealedCard.second)
        assertEquals(null, playerBAndDealedCard.second)
    }
}