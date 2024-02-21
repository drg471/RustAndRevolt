package com.drg.rustandrevolt.ui.screens.select_character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.service.AppContextSingleton
import com.drg.rustandrevolt.ui.navigation.AppScreens
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.BUTTON_COLOR
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE
import com.drg.rustandrevolt.viewmodels.CharacterSelectionViewModel

@Composable
fun SelectCharacterScreen(
    navigateToCombatScreen : () -> Unit,
    viewModel : CharacterSelectionViewModel = hiltViewModel()
    ) {
    val context = LocalContext.current
    AppContextSingleton.setContext(context)

    val buttonPlay : String = context.getString(R.string.button_play)

    viewModel.context = context

    //Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .border(1.dp, Color.Red)
            .background(Color(color = BACKGROUND_COLOR)),
    ) {

        //Controles seleccion tipo de personajes
        SelectCharacterTypeControls()

        //Controles seleccion tipos de personajes
        SelectCharacterControls()

        //Boton Jugar
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(Color(BUTTON_COLOR)),
            onClick = {
                viewModel.buttonPlaySound()
                navigateToCombatScreen()
            }
        ) {
            Text(
                text = buttonPlay,
                style = TextStyle(
                    fontFamily = FontFamily(TYPEFACE),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun SelectCharacterScreenPreview() {
    // Instancia del NavController
    val navController = rememberNavController()

    // Funcion de navegacion a la pantalla de combate
    fun navigateToCombatScreen() {
        navController.navigate(AppScreens.CombatScreen.route)
    }

    // Muestra la pantalla de seleccion de personaje
    SelectCharacterScreen(navigateToCombatScreen = { navigateToCombatScreen() })
}