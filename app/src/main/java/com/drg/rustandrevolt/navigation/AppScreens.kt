package com.drg.rustandrevolt.navigation

//Clase con las rutas de las pantallas para el navController
sealed class AppScreens(val route: String){
    object HomeScreen: AppScreens("home_screen")
    object OptionsScreen: AppScreens("options_screen")
    object SelectCharacterScreen: AppScreens("selectCharacter_screen")
    object CombatScreen: AppScreens("combat_screen")
}