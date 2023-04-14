package com.alagunas.highestcard.ui.items

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alagunas.highestcard.R

@Composable
fun TextWithWinner(text: String) {
    Text(
        text = text,
        color = colorResource(id = R.color.white),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TextWithNCards(modifier: Modifier, nCards: Int) {
    Text(
        text = stringResource(id = R.string.n_cards, nCards),
        color = colorResource(id = R.color.white),
        modifier = modifier.padding(top = 5.dp)
    )
}