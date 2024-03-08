package com.drg.rustandrevolt.ui.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.BUTTON_COLOR
import com.drg.rustandrevolt.ui.screens.home.HomeScreen
import com.drg.rustandrevolt.ui.screens.home.SEC_BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme
import com.drg.rustandrevolt.usecases.PlayerUseCase
import com.drg.rustandrevolt.viewmodels.PlayerViewModel

@Composable
fun PlayerScreen(navigateToHomeScreen : () -> Unit, viewModel: PlayerViewModel = hiltViewModel()) {
    val context = LocalContext.current
    viewModel.context = context
    val playerName : String = viewModel.mutablePlayerName
    val playerScore : String = viewModel.mutablePlayerScore
    val phraseOfDay : String = viewModel.phraseOfDay
    val buttonReturn : String = context.getString(R.string.button_return)

    viewModel.initRetrofit()

    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(color = BACKGROUND_COLOR)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //Frase del dia
        Text(
            text = phraseOfDay,
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color(SEC_BACKGROUND_COLOR))
                .padding(1.dp)
                .border(2.dp, Color.Black).padding(10.dp)
        )

        Spacer(modifier = Modifier.height(75.dp))

        //Nombre Player
        Text(
            text = playerName,
            style = TextStyle(
                fontFamily = FontFamily(TYPEFACE),
                fontSize = 42.sp,
                letterSpacing = 2.sp,
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Puntuacion Player
        Text(
            text = playerScore + " pts.",
            style = TextStyle(
                fontFamily = FontFamily(TYPEFACE),
                fontSize = 36.sp,
                letterSpacing = 2.sp,
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(75.dp))

        //Boton volver a pantalla Home
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
            , onClick = {
                viewModel.buttonSound()
                navigateToHomeScreen()
            }
        ) {
            Text(
                text = buttonReturn,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            )
        }

    }
}

@Composable
@Preview
fun PlayerScreenPreview() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    RustAndRevoltTheme(darkTheme = false){
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = LocalContentColor.current
        ) {
            PlayerScreen(
                navigateToHomeScreen = {},
            )
        }
    }
}