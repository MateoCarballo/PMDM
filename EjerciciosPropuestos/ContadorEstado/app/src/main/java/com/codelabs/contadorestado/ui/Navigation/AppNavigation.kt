package com.codelabs.contadorestado.ui.Navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.contadorestado.ui.Screens.CounterScreen
import com.codelabs.contadorestado.ui.Screens.HomeScreen
import com.codelabs.contadorestado.ui.Screens.ResultScreen
import com.codelabs.contadorestado.ui.State.CounterScreenViewModel

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
            val contadorViewModel: CounterScreenViewModel = viewModel()
            CounterScreen(
                estadoActual = contadorViewModel.state,
                sumarContador1 = {contadorViewModel.incrementarContador()},
                cambiarIncrementoContador1 = {contadorViewModel.cambiarIncremento()},
                vaciarContador1 = {contadorViewModel.resetearContador()},
                rutaUltimaPantalla = {navController.navigate(resultScreen.route)}
            )
        }
        composable(resultScreen.route)
        {
            val contadorViewModel: CounterScreenViewModel = viewModel()
            ResultScreen(
                resultadoSuma = contadorViewModel.devolverTotal(),
            )
        }
    }

}