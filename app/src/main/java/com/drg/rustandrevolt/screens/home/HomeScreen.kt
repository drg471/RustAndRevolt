package com.drg.rustandrevolt.screens.home

import android.content.Context
import android.content.res.Resources
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.drg.rustandrevolt.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToOptionsScreen : () -> Unit, navigateToSelectCharacterScreen : () -> Unit) {
    val context = LocalContext.current
    val appName : String = context.getString(R.string.app_name)

    //Scaffold con una barra de aplicaciones y un cuerpo de contenido simple
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(appName)
                },
                navigationIcon = {
                    //Icono de compartir app
                    Icon(Icons.Default.Share, contentDescription = "Share")
                }
            )
        }
    ) { paddingValues ->
        //Llama a la función que muestra los elementos de la pantalla Home
        HomeScreenElements(paddingValues = paddingValues, navigateToOptionsScreen, navigateToSelectCharacterScreen)
    }
}