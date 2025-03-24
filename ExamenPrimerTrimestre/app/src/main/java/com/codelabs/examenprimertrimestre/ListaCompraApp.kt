package com.codelabs.examenprimertrimestre

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codelabs.examenprimertrimestre.ui.theme.navigation.AppNavigation

@Composable
fun ListaCompraApp(navController: NavHostController = rememberNavController()) {
    AppNavigation(navController)
}