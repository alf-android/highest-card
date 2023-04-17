package com.alagunas.highestcard.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import buttonShape
import com.alagunas.highestcard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white_gray))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge,
                    color = colorResource(id = R.color.black)
                )

                val painter = painterResource(id = R.drawable.war_of_suits_transparent)
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 50.dp)
                        .width(200.dp)
                )

                MainButtonWithBorder(text = stringResource(id = R.string.play)) {
                    navController.navigate(route = "newgame")
                }

                MainButtonWithBorder(text = stringResource(id = R.string.historial)) {
                    /*TODO*/
                }
            }
        }
    }
}


@Composable
fun MainButtonWithBorder(text: String, action: () -> Unit) {
    Button(
        onClick = { action() },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.white),
            contentColor = colorResource(id = R.color.black)
        ),
        shape = buttonShape,
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.black))
    ) {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}