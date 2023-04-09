package com.alagunas.highestcard.ui.theme

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HighestCardTopAppBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Open Menu")
            }
        }
    )
}

@Preview(showBackground = false)
@Composable
fun HighestCardTopAppBarPreview() {
    HighestCardTopAppBar(title = "Title")
}