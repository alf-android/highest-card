package com.alagunas.highestcard.ui.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cardShape
import com.alagunas.domain.model.CardFaceName
import com.alagunas.highestcard.R

@Composable
fun CardItem(modifier: Modifier = Modifier, card: CardUI?) {
    card?.let {
        Card(
            modifier = modifier
                .width(120.dp)
                .height(150.dp),
            border = BorderStroke(1.dp, color = Color.Black),
            shape = cardShape,
            backgroundColor = Color.White
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(10.dp)
                ) {
                    Text(
                        text = it.faceName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    val painter = painterResource(id = it.suit)
                    Image(painter = painter, contentDescription = null)
                }

            }
        }
    } ?: run {
        Card(
            modifier = Modifier
                .width(80.dp)
                .height(100.dp),
            border = BorderStroke(1.dp, color = Color.Black),
            shape = cardShape,
            backgroundColor = colorResource(id = R.color.back_card_poker)
        ) {
            Column {
                repeat(16) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Divider(color = Color.White, thickness = 1.dp)
                }
            }

            Row {
                repeat(12) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Divider(
                        color = Color.White, modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CardPreview() {
    CardItem(modifier = Modifier, CardUI(CardFaceName.QUEEN.name, R.drawable.spades))
}

@Composable
@Preview
fun PilePreview() {
    CardItem(modifier = Modifier, null)
}