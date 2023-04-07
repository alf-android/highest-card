package com.alagunas.domain.model

data class Card(
    val faceName: CardFaceName,
    val suit: CardSuit
)

enum class CardFaceName(val value: Int, val show: String) {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A")
}

enum class CardSuit {
    CLUBS, DIAMONDS, HEARTS, SPADES,
}