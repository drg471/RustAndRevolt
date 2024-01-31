package com.drg.rustandrevolt.ui.screens.options

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.HomeScreen
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme

@Composable
fun OptionsScreen(
    navigateToHomeScreen : () -> Unit,
    navigateToInstructionsScreen : () -> Unit
) {
    val context = LocalContext.current
    val buttonSoundOnOff : String = context.getString(R.string.button_sound_onoff)
    val buttonInstructions : String = context.getString(R.string.button_instructions)
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

        //Boton Quitar Sonido App
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = { navigateToInstructionsScreen() }
        ) {
            Text(buttonInstructions)
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

@Composable
@Preview
fun OptionsScreenPreview() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    RustAndRevoltTheme(darkTheme = false){
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = LocalContentColor.current
        ) {
            OptionsScreen(
                navigateToHomeScreen = {},
                navigateToInstructionsScreen = {}
            )
        }
    }
}