package com.alagunas.domain

import com.alagunas.domain.model.DeckOfCards
import com.alagunas.domain.model.Player
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ModelTest {

    @Test
    fun shouldCreateNewDeck() {
        val deckOfCards = DeckOfCards()
        assertEquals(52, deckOfCards.pile1.size + deckOfCards.pile2.size)
    }

    @Test
    fun shouldRemoveACardFromPlayer() {
        val deckOfCards = DeckOfCards()
        val playerA = Player(deckOfCards.pile1)
        val playerB = Player(deckOfCards.pile2)
        assertEquals(26, playerA.getPileSize())
        assertEquals(26, playerB.getPileSize())

        val dealedCardA = playerA.dealTop()
        val dealedCardB = playerB.dealTop()
        println("playerA deck last: ${dealedCardA.faceName.show} ${dealedCardA.suit}")
        println("playerB deck last: ${dealedCardB.faceName.show} ${dealedCardB.suit}")
        assertEquals(25, playerA.getPileSize())
        assertEquals(25, playerB.getPileSize())

        val dealedCardA2 = playerA.dealTop()
        val dealedCardB2 = playerB.dealTop()
        println("playerA2 deck last: ${dealedCardA2.faceName.show} ${dealedCardA2.suit}")
        println("playerB2 deck last: ${dealedCardB2.faceName.show} ${dealedCardB2.suit}")
        assertEquals(24, playerA.getPileSize())
        assertEquals(24, playerB.getPileSize())
    }
}