package com.codelabs.contadorestado.ui.Navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codelabs.contadorestado.Data.Screen
import com.codelabs.contadorestado.ui.Screens.CounterScreen
import com.codelabs.contadorestado.ui.Screens.HomeScreen
import com.codelabs.contadorestado.ui.Screens.ResultScreen
import com.codelabs.contadorestado.ui.State.ContadorViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val homeScreen = Screen(route = "homeScreen")
    val counterScreen = Screen(route = "counterScreen")
    val resultScreen = Screen(route = "resultScreen")
    val contadorViewModel = ContadorViewModel()

    NavHost(
        navController = navController,
        startDestination = homeScreen.route
    )
    {
        //Mi idea es pasar el contardorViewModel y la navegacion en una sola cosa
        composable(
            route = homeScreen.route,
            arguments = contadorViewModel
        )
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
            CounterScreen(
                estado = contadorViewModel,
                nav = navController
            )
        }
        composable(resultScreen.route)
        {
            ResultScreen(
                estado = contadorViewModel,
            )
        }
    }

}