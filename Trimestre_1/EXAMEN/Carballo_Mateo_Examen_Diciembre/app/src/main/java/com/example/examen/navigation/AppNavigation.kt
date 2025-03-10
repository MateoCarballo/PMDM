package com.example.examen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen.model.getFakeProducts
import com.example.examen.ui.screen.DetailScreen
import com.example.examen.ui.screen.HomeScreen
import com.example.examen.ui.screen.PrincipalScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var products by remember { mutableStateOf(getFakeProducts()) }
    NavHost(
        navController = navController,
        startDestination = AppScreens.HOME_SCREEN.name
    )
    {
        composable(AppScreens.HOME_SCREEN.name) {
            HomeScreen(
                goToPrincipalScreen = {
                    navController.navigate(AppScreens.PRINCIPAL_SCREEN.name)
                }
            )
        }
        composable(AppScreens.PRINCIPAL_SCREEN.name) {
            PrincipalScreen(
                goToDetailScreen = {
                    navController.navigate(AppScreens.DETAILS_SCREEN.name)
                },
                restartApp = {
                    navController.popBackStack()
                }
            )
        }
        composable(AppScreens.DETAILS_SCREEN.name){
            DetailScreen( product = products.get(0),
                returnToPrincipal = {
                    navController.navigate(AppScreens.PRINCIPAL_SCREEN.name)
                    //TODO aqui no soy capaz de hacer que me pase el producto al que le pulso
                // la tecla de infor para poder escribir en la pantalla los valores propios

                }
            )
        }
    }
}