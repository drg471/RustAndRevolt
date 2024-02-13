package com.drg.rustandrevolt.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.viewmodels.HomeViewModel

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

    viewModel.context = context


    //Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color.Red)
            .padding(paddingValues)
        , Arrangement.Center
    ) {

        //Imagen Logo
        Image(
            painter = painterResource(R.drawable.logodflt),
            contentDescription = null,
            modifier = Modifier
                .size(width = 300.dp, height = 300.dp)
                .border(1.dp, Color.Black)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Boton Iniciar Partida
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigateToSelectCharacterScreen()
            }
        ) {
            Text(buttonStartGame)
        }

        //Boton Player
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigavigateToPlayerScreen()
        }
        ) {
            Text(buttonPlayer)
        }

        //Boton Opciones
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigateToOptionsScreen()
        }
        ) {
            Text(buttonOptions)
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
            }
        ) {
            Text(buttonExit)
        }
    }
}
