package com.drg.rustandrevolt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.screens.combat.CombatScreen
import com.drg.rustandrevolt.screens.home.HomeScreen
import com.drg.rustandrevolt.screens.options.OptionsScreen
import com.drg.rustandrevolt.screens.select_character.SelectCharacterScreen

@Composable
fun AppNavigation(){
    //Elemento navegador (para navegar entre pantallas de la app)
    val navController = rememberNavController()

    //NavHost con las llamadas a las pantallas
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreens.OptionsScreen.route){
            OptionsScreen(navController)
        }
        composable(route = AppScreens.SelectCharacterScreen.route){
            SelectCharacterScreen(navController)
        }
        composable(route = AppScreens.CombatScreen.route){
            CombatScreen(navController)
        }
    }
}