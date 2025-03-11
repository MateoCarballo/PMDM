package ejercicios.trivia.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        //TODO Aqui quiero pasar el numero de preguntas seleccionado en la pantalla home hacia la pantalla GAMESCREEN
        composable(
            AppScreens.HomeScreen.route,
        ) {
            HomeScreen(
                toGameScreen = { numberOfQuestions -> navController.navigate(AppScreens.GameScreen.route + "/$numberOfQuestions") },
            )
        }
        //TODO Aqui quiero pasar como parametro hacia la siguiente pantalla el numero de aciertos y el total de preguntas
        composable(
            AppScreens.GameScreen.route + "/{correctAnswers}",
            arguments = listOf(
                navArgument("correctAnswers") { type = NavType.StringType },
                navArgument("numberOfQuestions") { type = NavType.StringType }
            ),
        ) {
            val numberOfQuestions = it.arguments?.getString("numberOfQuestions") ?: "5"
            GameScreen(
                toResultScreen = {
                                 correctAnswers, numberOfQuestions ->
                    navController.navigate(AppScreens.ResultScreen.route + "/$correctAnswers" + "/$numberOfQuestions") },
                totalQuestions = numberOfQuestions,
            )

        }
        composable(AppScreens.ResultScreen.route) {
            val correctAnswers = it.arguments?.getString("correctAnswers") ?: "-1"
            val numberOfQuestions = it.arguments?.getString("numberOfQuestions") ?: "-1"
            ResultScreen(
                toHomeScreen = { navController.navigate(AppScreens.HomeScreen.route) },
                correctAnswers = correctAnswers,
                numberOfQuestions = numberOfQuestions,
            )
        }
    }
}

