package com.drg.rustandrevolt.ui.screens.home

import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.theme.RustAndRevoltTheme

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreenPreview() {
    val context = LocalContext.current
    val darkTheme = isSystemInDarkTheme()
    RustAndRevoltTheme(darkTheme = false){
        Surface(
            color = MaterialTheme.colorScheme.background,
            contentColor = LocalContentColor.current
        ) {
            HomeScreen(
                navigateToOptionsScreen = {},
                navigateToSelectCharacterScreen = {}
            )
        }
    }
}