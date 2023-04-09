package com.alagunas.highestcard.ui.screens.game

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alagunas.domain.model.Card
import com.alagunas.domain.model.CardFaceName
import com.alagunas.domain.model.CardSuit
import com.alagunas.highestcard.ui.items.CardItem
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: GameViewModel = getViewModel()
) {

    Scaffold(
        scaffoldState = scaffoldState,
//        topBar = { CocktailsTopAppBar("List of cocktails") }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)) {
                Text(text = "Player A")
                CardItem(card = Card(CardFaceName.QUEEN, CardSuit.HEARTS))
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Player B")
                CardItem(card = Card(CardFaceName.KING, CardSuit.CLUBS))
            }
        }
    }
}
