package com.codelabs.contadorestado.ui.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codelabs.contadorestado.ui.Screens.HomeScreen

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
        composable(homeScreen.route)
        {
            //Creo un composable homeScreen y le envio la lambda para cuando hagamos click en empezar
            HomeScreen(
                onStart = {
                    navController.navigate(counterScreen.route)
                }
            )
        }
        composable(counterScreen.route)
        {

        }
        composable(resultScreen.route)
        {

        }
    }

}