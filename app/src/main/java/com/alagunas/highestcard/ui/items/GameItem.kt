package com.alagunas.highestcard.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player

@Composable
fun GameItem(game: Game) {
    val modifier = Modifier
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Row {
            if (game.players.size == 2) {
                Column(modifier = modifier.padding(end = 20.dp)) {
                    Text(text = "Player A: ${game.players[0].wins} wins!")
                    Text(text = "Player B: ${game.players[1].wins} wins!")
                }
                Column {
                    when {
                        game.players[0].wins > game.players[1].wins -> {
                            Text(text = "WIN PLAYER A", fontSize = 20.sp)
                        }
                        game.players[0].wins < game.players[1].wins -> {
                            Text(text = "WIN PLAYER B", fontSize = 20.sp)
                        }
                        game.players[0].wins == game.players[1].wins -> {
                            Text(text = "DRAW", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GameItemPreview() {
    GameItem(game = Game(players = listOf(Player(wins = 20), Player(wins = 6))))
}
