package com.alagunas.domain

import com.alagunas.domain.model.DeckOfCards
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ModelTest {

    @Test
    fun shouldCreateNewDeck() {
        val deckOfCards = DeckOfCards()
        assertEquals(52, deckOfCards.deck.size)
    }
}