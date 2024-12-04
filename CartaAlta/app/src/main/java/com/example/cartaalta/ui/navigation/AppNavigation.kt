package com.example.cartaalta.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cartaalta.ui.screens.GameOverScreen
import com.example.cartaalta.ui.screens.GameScreen
import com.example.cartaalta.ui.screens.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable(AppScreens.HOME.name){ HomeScreen(navController) }
        composable(AppScreens.GAME.name){ GameScreen(navController) }
        composable(AppScreens.GAMEOVER.withArgs("{winner}")){
            navBackStackEntry -> val winner = navBackStackEntry.arguments?.getString("winner")
            GameOverScreen(navController,winner)
        }
    }
}
