package com.drg.rustandrevolt.ui.screens.options

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drg.rustandrevolt.R

@Composable
fun OptionsScreen(navigateToHomeScreen : () -> Unit) {
    val context = LocalContext.current
    val buttonSoundOnOff : String = context.getString(R.string.button_sound_onoff)
    val buttonReturn : String = context.getString(R.string.button_return)

    Column (modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Boton Quitar Sonido App
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = { }
        ) {
            Text(buttonSoundOnOff)
        }

        //Boton volver a pantalla Home
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = { navigateToHomeScreen() } //Vuelve a la ultima página guardada en pila
        ) {
            Text(buttonReturn)
        }
    }
}