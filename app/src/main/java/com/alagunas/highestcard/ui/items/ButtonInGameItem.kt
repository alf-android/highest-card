package com.alagunas.highestcard.ui.items

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import buttonShape
import com.alagunas.highestcard.R

@Composable
fun ButtonInGameItem(text: String, action: () -> Unit) {
    Button(
        onClick = { action() },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
        shape = buttonShape
    ) {
        Text(text = text)
    }
}

@Composable
@Preview
fun ButtonInGameItemPreview() {
    ButtonInGameItem(text = stringResource(id = R.string.next_round)) {

    }
}