package com.drg.rustandrevolt.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import androidx.compose.ui.text.font.FontFamily

val BACKGROUND_COLOR = 0xFF02474c //#02474c
val SEC_BACKGROUND_COLOR = 0xFF006679 //#006679
val BUTTON_COLOR = 0xFFf1883b //#f1883b
val TYPEFACE = Font(R.font.rumble_brave)


@Composable
fun HomeScreenElements(
    paddingValues: PaddingValues,
    navigateToOptionsScreen : () -> Unit,
    navigateToSelectCharacterScreen : () -> Unit,
    navigavigateToPlayerScreen : () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val buttonStartGame : String = context.getString(R.string.button_start_game)
    val buttonPlayer : String = context.getString(R.string.button_player)
    val buttonOptions : String = context.getString(R.string.button_options)
    val buttonExit : String = context.getString(R.string.button_exit)

    //Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .background(Color(color = BACKGROUND_COLOR))
        , Arrangement.Center
    ) {

        //Imagen Logo
        Image(
            painter = painterResource(R.drawable.rnrlogo),
            contentDescription = null,
            modifier = Modifier
                .size(width = 350.dp, height = 350.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        //Boton Iniciar Partida
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigateToSelectCharacterScreen()
            },
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
        ) {
            Text(
                text = buttonStartGame,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            )
        }

        //Boton Player
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigavigateToPlayerScreen()
            },
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
        ) {
            Text(
                text = buttonPlayer,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            )
        }

        //Boton Opciones
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigateToOptionsScreen()
            },
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
        ) {
            Text(
                text = buttonOptions,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Boton Salir
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                System.exit(0)
            },
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR))
        ) {
            Text(
                text = buttonExit,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            )
        }
    }
}
