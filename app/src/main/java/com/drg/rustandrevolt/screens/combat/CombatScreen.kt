package com.drg.rustandrevolt.screens.combat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CombatScreen(navigateToHomeScreen : () -> Unit) {

    //Columna Principal
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ){

        //Contenedor pantalla de juego
        GameScreen()

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        //Contenedor controles del jugador
        PlayerControls(navigateToHomeScreen)
    }
}