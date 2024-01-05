package com.drg.rustandrevolt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    //Elemento navegador (para navegar entre pantallas de la app)
    val navController = rememberNavController()

    //NavHost con las llamadas a las pantallas
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route){
            //PantallaInicioScaffold(navController)
        }
        composable(route = AppScreens.OptionsScreen.route){
            //SeleccionPersonajeScreen(navController)
        }
        composable(route = AppScreens.SelectCharacterScreen.route){
            //PantallaCombateScreen(navController)
        }
        composable(route = AppScreens.CombatScreen.route){
            //PantallaCombateScreen(navController)
        }
    }
}