package com.drg.rustandrevolt.screens.select_character

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drg.rustandrevolt.navigation.AppScreens

@Composable
fun selectCharacterScreen(navController: NavController) {

    //Columna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color.Red)
    ) {

        //Controles seleccion tipo de personajes
        SelectTypeControls.create()

        //Controles seleccion tipos de personajes
        SelectCharacterControls.create()

        //Boton Jugar
        Button(modifier = Modifier
            .background(Color.White)
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp),
            onClick = { navController.navigate(route = AppScreens.CombatScreen.route) }
        ) {
            Text("JUGAR")
        }
    }
}