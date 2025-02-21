package com.codelabs.contadorestado.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.contadorestado.ui.screens.CounterScreen
import com.codelabs.contadorestado.ui.screens.HomeScreen
import com.codelabs.contadorestado.ui.screens.ResultScreen

data class Screen (val route : String = "Sin definir")

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val homeScreen = Screen(route = "homeScreen")
    val counterScreen = Screen(route = "counterScreen")
    val resultScreen = Screen(route = "resultScreen")

    NavHost(
        navController = navController,
        startDestination = homeScreen.route
    )
    {
        composable(
            route = homeScreen.route
        )
        {
            HomeScreen(
                onStart = {
                    navController.navigate(counterScreen.route)
                }
            )
        }
        composable(counterScreen.route)
        {
            //TODO preguntar DANI como resolver las rutas
            // con parametros para poder navegar con datos
            // que vengan desde el counterScreen que es quien
            // lo llama
            CounterScreen(
                toResultScreen = {
                    navController.navigate(resultScreen.route)
                })
        }
        composable(resultScreen.route + "/{resultado}")
        {
            //TODO preguntar DANI como resolver las rutas
            // con parametros para poder navegar,
            // con datos que vengan desde el counterScreen
            // que es quien lo llama
            ResultScreen(
                toHomeScreen = {
                    navController.navigate(homeScreen.route)
                }
            )
        }
    }

}