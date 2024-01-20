package com.drg.rustandrevolt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.ui.screens.combat.CombatScreen
import com.drg.rustandrevolt.ui.screens.home.HomeScreen
import com.drg.rustandrevolt.ui.screens.options.OptionsScreen
import com.drg.rustandrevolt.ui.screens.select_character.SelectCharacterScreen

@Composable
fun AppNavigation(){
    //Elemento navegador (para navegar entre pantallas de la app)
    val navController = rememberNavController()

    //NavHost con las llamadas a las pantallas
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route) { navBackStackEntry ->
            val navigateToOptionsScreen: () -> Unit = {
                navController.navigate(AppScreens.OptionsScreen.route)
            }
            val navigateToSelectCharacterScreen: () -> Unit = {
                navController.navigate(AppScreens.SelectCharacterScreen.route)
            }
            HomeScreen(navigateToOptionsScreen, navigateToSelectCharacterScreen)
        }
        composable(route = AppScreens.OptionsScreen.route){ navBackStackEntry ->
            val navigateToHomeScreen: () -> Unit = {
                navController.navigate(AppScreens.HomeScreen.route)
            }
            OptionsScreen(navigateToHomeScreen)
        }
        composable(route = AppScreens.SelectCharacterScreen.route){ navBackStackEntry ->
            val navigateToCombatScreen : () -> Unit = {
                navController.navigate(AppScreens.CombatScreen.route)
            }
            SelectCharacterScreen(navigateToCombatScreen)
        }
        composable(route = AppScreens.CombatScreen.route){ navBackStackEntry ->
            val navigateToHomeScreen : () -> Unit = {
                navController.navigate(AppScreens.HomeScreen.route)
            }
            CombatScreen(navigateToHomeScreen)
        }
    }
}