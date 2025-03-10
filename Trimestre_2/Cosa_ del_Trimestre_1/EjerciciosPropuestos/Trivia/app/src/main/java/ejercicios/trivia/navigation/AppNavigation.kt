package ejercicios.trivia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ejercicios.trivia.ui.screens.GameScreen
import ejercicios.trivia.ui.screens.HomeScreen
import ejercicios.trivia.ui.screens.ResultScreen
import ejercicios.trivia.ui.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen,
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen()
        }
        //TODO pendiente de agregar parametros para navegar con el numero de preguntas
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                toGameScreen = {navController.navigate(AppScreens.GameScreen.route)},
            )
        }
        //TODO pendiente de agregar parametros para navegar con los aciertos hacia la pantalla resultados
        composable(AppScreens.GameScreen.route) {
            GameScreen(
                toResultScreen = {navController.navigate(AppScreens.ResultScreen.route)},
            )

        }
        composable(AppScreens.ResultScreen.route) {
            ResultScreen(
                toHomeScreen = {navController.navigate(AppScreens.HomeScreen.route)},
            )
        }
    }
}