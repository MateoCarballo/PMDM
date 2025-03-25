package com.codelabs.examenprimertrimestre.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.codelabs.examenprimertrimestre.ui.theme.screens.Detalle
import com.codelabs.examenprimertrimestre.ui.theme.screens.Home
import com.codelabs.examenprimertrimestre.ui.theme.screens.ListaCompra

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.route,
    ) {
        composable(AppScreens.HomeScreen.route) {
            Home(
                toListScreen = { navController.navigate(AppScreens.ListaCompraScreen.route) }
            )
        }
        composable(AppScreens.ListaCompraScreen.route) {
            ListaCompra(
                navigateBack = { navController.popBackStack() },
                toDetailScreen = { productName, productPrice, productQuantity -> navController.navigate(AppScreens.DetalleScreen.route + "/$productName" + "/$productPrice" + "/$productQuantity") }
            )
        }
        composable(
            AppScreens.DetalleScreen.route + "/{productName}/{productPrice}/{productQuantity}",
            arguments = listOf(
                navArgument("productName") { type = NavType.StringType },
                navArgument("productPrice") { type = NavType.StringType },
                navArgument("productQuantity") { type = NavType.StringType }
            )
        ) {
            val productName = it.arguments?.getString("productName") ?: ""
            val productPrice = it.arguments?.getString("productPrice") ?: ""
            val productQuantity = it.arguments?.getString("productQuantity") ?: ""
            Detalle(
                //TODO pierdo los valores de los contadores cuando voy a detalles si uso popbackstack()
                //navigateBack = { navController.popBackStack() },
                navigateBack = { navController.navigate(AppScreens.ListaCompraScreen.route) },
                toHomeScreen = { navController.navigate(AppScreens.HomeScreen.route) },
                productName = productName,
                productPrice = productPrice,
                productQuantity = productQuantity,
            )
        }
    }

}