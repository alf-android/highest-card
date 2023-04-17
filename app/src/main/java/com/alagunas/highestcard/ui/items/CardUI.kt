package com.alagunas.highestcard.ui.items

import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardSuit
import com.alagunas.highestcard.R

data class CardUI(
    val faceName: String,
    val suit: Int
)

fun Card.toCardUI(): CardUI =
    CardUI(
        faceName = this.faceName.show,
        suit = this.suit.getThumb()
    )

fun CardSuit.getThumb(): Int =
    when (this) {
        CardSuit.DIAMONDS -> R.drawable.diamonds
        CardSuit.HEARTS -> R.drawable.hearts
        CardSuit.CLUBS -> R.drawable.clubs
        CardSuit.SPADES -> R.drawable.spades
    }