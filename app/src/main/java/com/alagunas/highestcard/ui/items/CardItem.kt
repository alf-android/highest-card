package com.alagunas.highestcard.ui.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cardShape
import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardFaceName
import com.alagunas.domain.model.CardSuit

@Composable
fun CardItem(card: Card) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Dealed card:", modifier = Modifier.padding(5.dp))
        Card(
            modifier = Modifier
                .width(120.dp)
                .height(150.dp),
            border = BorderStroke(1.dp, color = Color.Black),
            shape = cardShape,
            backgroundColor = Color.White,

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "${card.faceName.show} ${card.suit}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}

@Composable
@Preview
fun CardPreview() {
    CardItem(Card(CardFaceName.QUEEN, CardSuit.HEARTS))
}