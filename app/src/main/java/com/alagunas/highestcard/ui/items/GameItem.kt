package com.alagunas.highestcard.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alagunas.domain.model.Game
import com.alagunas.domain.model.Player
import com.alagunas.highestcard.R

@Composable
fun GameItem(game: Game) {
    val modifier = Modifier
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row {
            if (game.players.size == 2) {
                Column(modifier = modifier.padding(end = 20.dp)) {
                    val professorX = stringResource(id = R.string.professor_x)
                    val magneto = stringResource(id = R.string.magneto)
                    val playerAWins = stringResource(id = R.string.n_wins, game.players[0].wins)
                    val playerBWins = stringResource(id = R.string.n_wins, game.players[1].wins)
                    Text(
                        text = StringBuilder().append(professorX).append(": ").append(playerAWins)
                            .toString(), color = colorResource(id = R.color.black)
                    )
                    Text(
                        text = StringBuilder().append(magneto).append(": ").append(playerBWins)
                            .toString(), color = colorResource(id = R.color.black)
                    )
                }
                Column {
                    when {
                        game.players[0].wins > game.players[1].wins -> {
                            Text(
                                text = stringResource(id = R.string.win_professorx),
                                color = colorResource(id = R.color.black),
                                fontSize = 16.sp
                            )
                        }
                        game.players[0].wins < game.players[1].wins -> {
                            Text(
                                text = stringResource(id = R.string.win_magneto),
                                color = colorResource(id = R.color.black),
                                fontSize = 16.sp
                            )
                        }
                        game.players[0].wins == game.players[1].wins -> {
                            Text(
                                text = stringResource(id = R.string.draw),
                                color = colorResource(id = R.color.black),
                                fontSize = 16.sp
                            )
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
