package ejercicios.trivia

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ejercicios.trivia.navigation.AppNavigation

@Composable
fun TriviaApp(navController: NavHostController = rememberNavController()){
    AppNavigation(navController)
}