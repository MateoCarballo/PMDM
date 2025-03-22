package ejercicios.trivia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
fun AppNavigation(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route,
    ) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen { navController.navigate(AppScreens.HomeScreen.route) }
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
            AppScreens.GameScreen.route + "/{numberOfQuestions}",
            arguments = listOf(
                navArgument("numberOfQuestions") { type = NavType.StringType }
            ),
        ) {
            val numberOfQuestions = it.arguments?.getString("numberOfQuestions") ?: "5"
            GameScreen(
                toResultScreen = {
                                 corrects, totalQuestionsNumber ->
                    navController.navigate(AppScreens.ResultScreen.route + "/$corrects" + "/$totalQuestionsNumber") },
                totalQuestions = numberOfQuestions,

            )

        }
        composable(AppScreens.ResultScreen.route+ "/{correctAnswers}" + "/{numberOfQuestions}",
            arguments = listOf(
                navArgument("correctAnswers") { type = NavType.StringType },
                navArgument("numberOfQuestions") { type = NavType.StringType })
        ) {
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

