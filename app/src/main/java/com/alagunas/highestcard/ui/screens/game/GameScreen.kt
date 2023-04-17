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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import buttonShape
import com.alagunas.domain.model.CardFaceName
import com.alagunas.highestcard.R
import com.alagunas.highestcard.ui.items.*
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

    val discardPilePlayerA by viewModel.showDiscardPilePlayerA.collectAsState()
    val discardPilePlayerB by viewModel.showDiscardPilePlayerB.collectAsState()

    val winRoundPlayerA by viewModel.showWinRoundPlayerA.collectAsState()
    val winRoundPlayerB by viewModel.showWinRoundPlayerB.collectAsState()

    val visibilityNextRound by viewModel.showNextRound.collectAsState()

    val winner by viewModel.showWinner.collectAsState()

    val suitsOrder by viewModel.showSuitsOrder.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        val modifier = Modifier
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.green_poker))
        ) {
            Column(modifier = modifier.padding(top = 20.dp)) {
                SuitsOrderItem(modifier, suitsOrder)
                TwoPilesLayDownItem(modifier, pilePlayerA, pilePlayerB)
                DealCardsItem(modifier, cardPlayerA, cardPlayerB, winRoundPlayerA, winRoundPlayerB)
                DiscardPilesItem(
                    modifier, discardPilePlayerA, discardPilePlayerB,
                    (cardPlayerA != null && cardPlayerB != null)
                )
                Box(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp)
                ) {
                    winner?.let {
                        when (it) {
                            1 -> TextWithWinner(
                                stringResource(
                                    id = R.string.winner_x, stringResource(
                                        id = R.string.professor_x
                                    )
                                )
                            )
                            2 -> TextWithWinner(
                                stringResource(
                                    id = R.string.winner_x, stringResource(
                                        id = R.string.magneto
                                    )
                                )
                            )
                            else -> TextWithWinner("DRAW!")
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp)
            ) {
                if (visibilityNextRound) ButtonInGameItem(stringResource(id = R.string.next_round)) { viewModel.nextRound() }
                Spacer(modifier = modifier.width(20.dp))
                ButtonInGameItem(stringResource(id = R.string.reset_game)) { viewModel.resetGame() }
            }
        }
    }
}

@Composable
fun SuitsOrderItem(modifier: Modifier, suitsOrder: List<Int>) {
    Row(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.suits_order),
            color = colorResource(id = R.color.white),
            fontSize = 18.sp,
            modifier = modifier.padding(end = 5.dp)
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
}

@Composable
fun TwoPilesLayDownItem(modifier: Modifier, pilePlayerA: Int?, pilePlayerB: Int?) {
    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(start = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.professor_x),
                color = colorResource(id = R.color.white),
                fontSize = 22.sp,
                modifier = modifier.padding(bottom = 20.dp)
            )
            pilePlayerA?.let {
                if (it > 0) CardItem(card = null)
                TextWithNCards(modifier, it)
            }
        }

        Column(
            modifier = modifier
                .align(Alignment.CenterVertically)
                .padding(end = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.magneto),
                color = colorResource(id = R.color.white),
                fontSize = 22.sp,
                modifier = modifier.padding(bottom = 20.dp)
            )
            pilePlayerB?.let {
                if (it > 0) CardItem(card = null)
                TextWithNCards(modifier, it)
            }

        }
    }
}

@Composable
fun DealCardsItem(
    modifier: Modifier, cardPlayerA: CardUI?,
    cardPlayerB: CardUI?,
    winRoundPlayerA: Boolean?,
    winRoundPlayerB: Boolean?
) {
    Row(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        cardPlayerA?.let {
            if (winRoundPlayerA == true) {
                CardItem(
                    modifier = Modifier
                        .width(120.dp)
                        .height(150.dp), card = it
                )
            } else if (winRoundPlayerA == false) {
                CardItem(
                    modifier = Modifier
                        .width(80.dp)
                        .height(100.dp), card = it
                )
            }
        }
        Spacer(modifier = modifier.width(10.dp))
        cardPlayerB?.let {
            if (winRoundPlayerB == true) {
                CardItem(
                    modifier = Modifier
                        .width(120.dp)
                        .height(150.dp), card = it
                )
            } else if (winRoundPlayerB == false) {
                CardItem(
                    modifier = Modifier
                        .width(80.dp)
                        .height(100.dp), card = it
                )
            }
        }
    }
}

@Composable
fun DiscardPilesItem(
    modifier: Modifier,
    discardPilePlayerA: Int,
    discardPilePlayerB: Int,
    visibility: Boolean
) {
    Row(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (visibility) {
            Box {
                Column(
                    modifier = modifier
                        .align(Alignment.Center)
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.discard_pile),
                        color = colorResource(id = R.color.white),
                        modifier = modifier.padding(bottom = 5.dp)
                    )
                    if (discardPilePlayerA > 0) CardItem(card = null)
                    TextWithNCards(modifier, discardPilePlayerA)
                }
            }
            Box {
                Column(
                    modifier = modifier
                        .align(Alignment.Center)
                        .padding(end = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.discard_pile),
                        color = colorResource(id = R.color.white),
                        modifier = modifier.padding(bottom = 5.dp)
                    )
                    if (discardPilePlayerB > 0) CardItem(card = null)
                    TextWithNCards(modifier, discardPilePlayerB)
                }
            }
        }
    }
}