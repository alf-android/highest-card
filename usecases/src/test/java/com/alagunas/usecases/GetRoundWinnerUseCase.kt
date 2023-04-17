package com.alagunas.usecases

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardFaceName
import com.alagunas.domain.model.CardSuit
import com.alagunas.usecases.game.getroundwinner.BodyGetRoundWinner
import com.alagunas.usecases.game.getroundwinner.GetRoundWinnerUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetRoundWinnerUseCase {

    private lateinit var getRoundWinnerUseCase: GetRoundWinnerUseCase
    private lateinit var order: List<CardSuit>

    @Before
    fun setUp() {
        getRoundWinnerUseCase = GetRoundWinnerUseCase()
        order = listOf(
            CardSuit.CLUBS,
            CardSuit.HEARTS,
            CardSuit.SPADES,
            CardSuit.DIAMONDS
        )
    }

    @Test
    fun shouldWinQueenAgainstJack() {
        val winnerCard = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.QUEEN, CardSuit.DIAMONDS),
                    Card(CardFaceName.JACK, CardSuit.CLUBS)
                )
            )
        )
        assertEquals(Card(CardFaceName.QUEEN, CardSuit.DIAMONDS), winnerCard)
    }

    @Test
    fun shouldWinKingAgainstQueen() {
        val winnerCard = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.QUEEN, CardSuit.DIAMONDS),
                    Card(CardFaceName.KING, CardSuit.CLUBS)
                )
            )
        )
        assertEquals(Card(CardFaceName.KING, CardSuit.CLUBS), winnerCard)
    }

    @Test
    fun shouldWinAceAgainstKing() {
        val winnerCard = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.ACE, CardSuit.DIAMONDS),
                    Card(CardFaceName.KING, CardSuit.CLUBS)
                )
            )
        )
        assertEquals(Card(CardFaceName.ACE, CardSuit.DIAMONDS), winnerCard)
    }

    @Test
    fun shouldWinAceAgainstNumber() {
        val winnerCard = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.ACE, CardSuit.DIAMONDS),
                    Card(CardFaceName.TWO, CardSuit.CLUBS)
                )
            )
        )
        assertEquals(Card(CardFaceName.ACE, CardSuit.DIAMONDS), winnerCard)
    }

    @Test
    fun checkOrderInCaseOfSameCardFace() {
        val winnerCardClubsBeforeDiamonds = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.QUEEN, CardSuit.DIAMONDS),
                    Card(CardFaceName.QUEEN, CardSuit.CLUBS)
                )
            )
        )
        assertEquals(Card(CardFaceName.QUEEN, CardSuit.CLUBS), winnerCardClubsBeforeDiamonds)

        val winnerCardHeartsBeforeSpades = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.TWO, CardSuit.HEARTS),
                    Card(CardFaceName.TWO, CardSuit.SPADES)
                )
            )
        )
        assertEquals(Card(CardFaceName.TWO, CardSuit.HEARTS), winnerCardHeartsBeforeSpades)
    }

    @Test
    fun cardFaceAlwaysWinsBeforeTheSuit() {
        val winnerCardSameSuit = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.THREE, CardSuit.DIAMONDS),
                    Card(CardFaceName.TWO, CardSuit.DIAMONDS)
                )
            )
        )
        assertEquals(Card(CardFaceName.THREE, CardSuit.DIAMONDS), winnerCardSameSuit)

        val winnerCardMostValue = getRoundWinnerUseCase(
            BodyGetRoundWinner(
                order,
                Pair(
                    Card(CardFaceName.THREE, CardSuit.DIAMONDS),
                    Card(CardFaceName.TWO, CardSuit.CLUBS)
                )
            )
        )
        assertEquals(Card(CardFaceName.THREE, CardSuit.DIAMONDS), winnerCardMostValue)
    }
}