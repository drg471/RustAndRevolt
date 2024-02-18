package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.ui.navigation.AppScreens
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import com.drg.rustandrevolt.ui.screens.home.HomeScreen
import com.drg.rustandrevolt.ui.screens.select_character.SelectCharacterScreen

@Composable
fun CombatScreen(navigateToHomeScreen : () -> Unit) {

    //Columna Principal
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(color = BACKGROUND_COLOR)),
    ){

        //Contenedor pantalla de juego
        GameScreen()

        //Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

        //Contenedor controles del jugador
        PlayerControls(navigateToHomeScreen)
    }
}

@Preview
@Composable
fun CombatScreenPreview() {
    val navController = rememberNavController()

    fun navigateToHomeScreen() {
        navController.navigate(AppScreens.HomeScreen.route)
    }
    fun navigateToSelectCharacterScreen() {
        navController.navigate(AppScreens.HomeScreen.route)
    }

    CombatScreen(
        navigateToHomeScreen = { navigateToHomeScreen() }
    )
}