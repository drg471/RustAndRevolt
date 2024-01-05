package com.drg.rustandrevolt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.screens.combat.combatScreen
import com.drg.rustandrevolt.screens.home.homeScreen
import com.drg.rustandrevolt.screens.options.optionsScreen
import com.drg.rustandrevolt.screens.select_character.selectCharacterScreen

@Composable
fun AppNavigation(){
    //Elemento navegador (para navegar entre pantallas de la app)
    val navController = rememberNavController()

    //NavHost con las llamadas a las pantallas
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route){
            homeScreen(navController)
        }
        composable(route = AppScreens.OptionsScreen.route){
            optionsScreen(navController)
        }
        composable(route = AppScreens.SelectCharacterScreen.route){
            selectCharacterScreen(navController)
        }
        composable(route = AppScreens.CombatScreen.route){
            combatScreen(navController)
        }
    }
}