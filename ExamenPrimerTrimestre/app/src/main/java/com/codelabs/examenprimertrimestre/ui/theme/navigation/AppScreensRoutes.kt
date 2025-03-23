package com.codelabs.examenprimertrimestre.ui.theme.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen : AppScreens("home_screen")
    data object DetalleScreen : AppScreens("detalle_screen")
    data object ListaCompraScreen : AppScreens("lista_compra_screen")
}