package ejercicios.trivia.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen : AppScreens("home_screen")
    data object GameScreen : AppScreens("game_screen")
    data object ResultScreen : AppScreens("result_screen")
    data object SplashScreen : AppScreens("splash_screen")
}