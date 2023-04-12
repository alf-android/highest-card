package com.alagunas.highestcard.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alagunas.highestcard.ui.items.CardItem
import org.koin.androidx.compose.getViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import buttonShape
import com.alagunas.highestcard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: GameViewModel = getViewModel()
) {

    val pilePlayerA by viewModel.showPilePlayerA.collectAsState()
    val pilePlayerB by viewModel.showPilePlayerB.collectAsState()

    val cardPlayerA by viewModel.showCardPlayerA.collectAsState()
    val cardPlayerB by viewModel.showCardPlayerB.collectAsState()

    val winsPlayerA by viewModel.showWinsPlayerA.collectAsState()
    val winsPlayerB by viewModel.showWinsPlayerB.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.green_poker))
        ) {
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 100.dp)) {
                Text(text = "Player A", color = colorResource(id = R.color.white))
                Text(
                    text = "Dealed card:",
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
                Row {
                    pilePlayerA?.let {
                        CardItem(card = cardPlayerA)
                        Spacer(modifier = Modifier.width(20.dp))
                        if (it > 0) CardItem(card = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Column {
                            Text(text = "$it cards left", color = colorResource(id = R.color.white))
                            Text(
                                text = "$winsPlayerA wins!",
                                color = colorResource(id = R.color.white)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Player B", color = colorResource(id = R.color.white))
                Text(
                    text = "Dealed card:",
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.padding(5.dp)
                )
                Row {
                    pilePlayerB?.let {
                        CardItem(card = cardPlayerB)
                        Spacer(modifier = Modifier.width(20.dp))
                        if (it > 0) CardItem(card = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Column {
                            Text(text = "$it cards left", color = colorResource(id = R.color.white))
                            Text(
                                text = "$winsPlayerB wins!",
                                color = colorResource(id = R.color.white)
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
            ) {
                Button(
                    onClick = { viewModel.nextRound() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = buttonShape
                ) {
                    Text(text = stringResource(id = R.string.next_round))
                }

                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { viewModel.resetGame() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = buttonShape
                ) {
                    Text(text = stringResource(id = R.string.reset_game))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun GameScreenPreview() {

}
