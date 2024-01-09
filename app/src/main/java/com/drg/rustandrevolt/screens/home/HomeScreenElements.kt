package com.drg.rustandrevolt.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.navigation.AppScreens

@Composable
fun HomeScreenElements(paddingValues: PaddingValues, navController: NavController) {

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

        //Boton Iniciar Partida
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = { navController.navigate(route = AppScreens.SelectCharacterScreen.route) }
        ) {
            Text("INICIAR PARTIDA")
        }

        //Boton Opciones
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 16.dp)
            .height(40.dp)
            .width(200.dp), onClick = { navController.navigate(route = AppScreens.OptionsScreen.route) }
        ) {
            Text("OPCIONES")
        }
    }
}
