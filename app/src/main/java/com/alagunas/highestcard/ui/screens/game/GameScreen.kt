package com.alagunas.highestcard.ui.screens.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import buttonShape
import com.alagunas.domain.model.CardFaceName
import com.alagunas.highestcard.R
import com.alagunas.highestcard.ui.items.CardItem
import com.alagunas.highestcard.ui.items.CardUI
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: GameViewModel = getViewModel()
) {

    val pilePlayerA by viewModel.showPilePlayerA.collectAsState()
    val pilePlayerB by viewModel.showPilePlayerB.collectAsState()

    val cardPlayerA by viewModel.showCardPlayerA.collectAsState()
    val cardPlayerB by viewModel.showCardPlayerB.collectAsState()

    val winsPlayerA by viewModel.showWinsPlayerA.collectAsState()
    val winsPlayerB by viewModel.showWinsPlayerB.collectAsState()

    val suitsOrder by viewModel.showSuitsOrder.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.green_poker))
        ) {

            Column(modifier = Modifier.padding(top = 30.dp)) {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Suits order:",
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    suitsOrder.forEach {
                        val painter = painterResource(id = it)
                        Image(
                            painter = painter, contentDescription = null, modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.professor_x),
                            color = colorResource(id = R.color.white),
                            fontSize = 22.sp,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                        pilePlayerA?.let {
                            if (it > 0) CardItem(card = null)
                        }
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.magneto),
                            color = colorResource(id = R.color.white),
                            fontSize = 22.sp,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )
                        pilePlayerB?.let {
                            if (it > 0) CardItem(card = null)
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    cardPlayerA?.let {
                        CardItem(card = it)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    cardPlayerB?.let {
                        CardItem(card = it)
                    }
                }
                /*Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Player A WIN!",
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp
                    )
                }*/
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box {
                        //CardItem(card = null)
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                        ) {
                            Text(text = "winner Pile:", color = colorResource(id = R.color.white))
                            Text(
                                text = "${(winsPlayerA * 2)} cards",
                                color = colorResource(id = R.color.white)
                            )
                        }
                    }

                    Box {
                        //CardItem(card = null)
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                        ) {
                            Text(text = "winner Pile:", color = colorResource(id = R.color.white))
                            Text(
                                text = "${(winsPlayerB * 2)} cards",
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
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                    shape = buttonShape
                ) {
                    Text(text = stringResource(id = R.string.next_round))
                }

                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { viewModel.resetGame() },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                    shape = buttonShape
                ) {
                    Text(text = stringResource(id = R.string.reset_game))
                }
            }
        }
    }
}

@Composable
fun GameInside() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.green_poker))
    ) {

        Column {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Player A",
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                )
                Text(
                    text = "Player B",
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(end = 20.dp, top = 20.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardItem(card = null)
                CardItem(card = null)
            }
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CardItem(card = CardUI(CardFaceName.KING.show, R.drawable.clubs))
                CardItem(card = CardUI(CardFaceName.QUEEN.show, R.drawable.hearts))
            }
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Player A WIN!",
                    color = colorResource(id = R.color.white),
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    CardItem(card = null)
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Text(text = "winner Pile:")
                        Text(text = "2 cards")
                    }
                }
                Box {
                    CardItem(card = null)
                    Column(
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Text(text = "winner Pile:")
                        Text(text = "5 cards")
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
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                shape = buttonShape
            ) {
                Text(text = stringResource(id = R.string.next_round))
            }

            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black)),
                shape = buttonShape
            ) {
                Text(text = stringResource(id = R.string.reset_game))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun GameScreenPreview() {
    GameInside()
}
