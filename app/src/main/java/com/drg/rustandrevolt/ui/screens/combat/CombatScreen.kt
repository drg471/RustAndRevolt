package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.navigation.AppScreens
import com.drg.rustandrevolt.ui.screens.home.BACKGROUND_COLOR
import kotlinx.coroutines.delay

@Composable
fun CombatScreen(navigateToHomeScreen: () -> Unit) {
    DelayedComposables(navigateToHomeScreen)
}

@Composable
fun GameScreenLoading() {
    val context = LocalContext.current
    val loadingText : String = context.getString(R.string.loadingText)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(loadingText, color = Color.White)
        }
    }
}

@Composable
fun CombatScreenElements(navigateToHomeScreen: () -> Unit) {
    //Columna Principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = BACKGROUND_COLOR)),
    ) {
        //Contenedor pantalla de juego
        GameScreen()

        //Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

        //Contenedor controles del jugador
        PlayerControls(navigateToHomeScreen)
    }
}

@Composable
fun DelayedComposables(navigateToHomeScreen: () -> Unit) {
    val showSecondComposable = remember { mutableStateOf(false) }

    // Utilizamos LaunchedEffect para iniciar un efecto cuando el Composable se carga
    LaunchedEffect(true) {
        // Mostrar el primer Composable
        delay(2000) // Esperar 2 segundos
        // Cambiar el estado para mostrar el segundo Composable después de 2 segundos
        showSecondComposable.value = true
    }

    // Mostrar el primer Composable por defecto
    GameScreenLoading()

    // Mostrar el segundo Composable después de 2 segundos
    if (showSecondComposable.value) {
        CombatScreenElements(navigateToHomeScreen)
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