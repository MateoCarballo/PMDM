package com.pmdm.ejemplorapidonavegacion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.listacompraestado.ui.navigation.Screens
import com.codelabs.listacompraestado.ui.screens.HomeScreen
import com.codelabs.listacompraestado.ui.screens.ListScreen

@Composable
fun NavigationGrapho() {
    val navController = rememberNavController()

    // Navigation
    NavHost(navController = navController, startDestination = Screens.HOME_SCREEN.name) {
        composable(Screens.HOME_SCREEN.name) {
            HomeScreen(
                toListScreen = {navController.navigate(Screens.LIST_SCREEN.name)}
            )
        }
        composable(Screens.LIST_SCREEN.name) {
            ListScreen(
                toHomeScreen = { navController.popBackStack() }
            )
        }

    }


}