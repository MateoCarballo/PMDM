package com.codelabs.examenprimertrimestre.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codelabs.examenprimertrimestre.ui.theme.screens.Detalle
import com.codelabs.examenprimertrimestre.ui.theme.screens.Home
import com.codelabs.examenprimertrimestre.ui.theme.screens.ListaCompra


@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.route,
    ){
        composable(AppScreens.HomeScreen.route){
            Home(
                toListScreen = {navController.navigate(AppScreens.ListaCompraScreen.route)}
            )
        }
        composable(AppScreens.ListaCompraScreen.route) {
            ListaCompra(
                toDetailScreen = {navController.navigate(AppScreens.DetalleScreen.route)}
            )
        }
        composable(AppScreens.DetalleScreen.route) {
            Detalle(
                toHomeScreen = {navController.navigate(AppScreens.HomeScreen.route)}
            )
        }
    }

}