package com.codelabs.contadoresestadonavegacion.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.contadoresestadonavegacion.Data.Screen
import com.codelabs.contadoresestadonavegacion.ui.Screens.CounterScreen
import com.codelabs.contadoresestadonavegacion.ui.Screens.HomeScreen
import com.codelabs.contadoresestadonavegacion.ui.Screens.ResultScreen
import com.codelabs.contadoresestadonavegacion.ui.State.CounterViewModel

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val homeScreen = Screen(route = "HomeScreen")
    val counterScreen = Screen(route = "CounterScreen")
    val resultScreen = Screen(route = "ResultScreen")
    val counterVM = CounterViewModel()

    NavHost(
        navController = navController,
        startDestination = homeScreen.route
    ) {
        //Pantalla de inicio
        composable(homeScreen.route) {
            HomeScreen (
                toCounterScreen = {navController.navigate(counterScreen.route) },
                generarContadores = {counterVM.crearContadoresConParametro()}
            )
        }
        //Pantalla de contadores
        composable(counterScreen.route) {
            //TODO pensar bien como llegar a hacer esto pero sin pasar todo el vm
            CounterScreen(
                toResultScreen = {
                  navController.navigate(resultScreen.route)
                                },
                numeroContadores = counterVM.contadoresCreadosEnLaLista()
                ,
                actualizarIncrementos = {
                    counterVM.modificarIncremento()
                },
                actualizarTotales = {
                    counterVM.modificarTotal()
                }
            )
        }
        //Pantalla resultados
        composable(resultScreen.route) {
            ResultScreen(
                toHomeScreen = {
                    navController.navigate(homeScreen.route)
                },
                sumaContadores = counterVM.sumaContadores()
            )
        }
    }
}