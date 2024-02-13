package com.drg.rustandrevolt.ui.screens.options

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.hilt.MyApplication.Companion.musicPreferences
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme
import com.drg.rustandrevolt.viewmodels.HomeViewModel
import com.drg.rustandrevolt.viewmodels.OptionsViewModel

@Composable
fun OptionsScreen(
    navigateToHomeScreen : () -> Unit,
    navigateToInstructionsScreen : () -> Unit,
    viewModel: OptionsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.context = context

    val buttonSoundOnOff : String = context.getString(R.string.button_sound_onoff)
    val buttonInstructions : String = context.getString(R.string.button_instructions)
    val buttonReturn : String = context.getString(R.string.button_return)

    Column (modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        Row(){
            //Switch Quitar Sonido App
            Text(buttonSoundOnOff)
            Switch(
                checked = musicPreferences.isMusicEnabled,
                onCheckedChange = { isChecked ->
                    viewModel.buttonSound()
                    musicPreferences.setMusicEnabledPrefs(isChecked, true)
                },
                modifier = Modifier.padding(top = 10.dp)
            )
        }


        //Boton Instrucciones
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigateToInstructionsScreen()
            }
        ) {
            Text(buttonInstructions)
        }

        //Boton volver a pantalla Home
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = {
                viewModel.buttonSound()
                navigateToHomeScreen()
            } //Vuelve a la ultima página guardada en pila
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