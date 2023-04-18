package com.alagunas.highestcard.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alagunas.domain.model.Game
import com.alagunas.highestcard.R
import com.alagunas.highestcard.ui.items.GameItem
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: HistoryViewModel = getViewModel()
) {

    val games: List<Game> by viewModel.showGames.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        val modifier = Modifier
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white_gray))
        ) {
            if (games.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(games) { game ->
                        GameItem(game)
                        Divider(
                            modifier = Modifier.padding(top = 5.dp),
                            color = Color.Black,
                            thickness = 1.dp
                        )
                    }
                }
            } else {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.not_games_found), fontSize = 16.sp
                )
            }
        }
    }
}