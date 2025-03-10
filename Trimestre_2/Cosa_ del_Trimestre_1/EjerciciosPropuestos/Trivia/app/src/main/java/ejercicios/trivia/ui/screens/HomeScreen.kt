package ejercicios.trivia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ejercicios.trivia.navigation.AppScreens

@Composable
fun HomeScreen(
    toGameScreen: () -> Unit,
) {
    Column(

    ) {
        Text("Bienvenido a Trivia App")
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Button(
            //TODO Pendiente de configurar el sumar una a las preguntas totales con las que jugaremos
            onClick = {}
        ) { }
    }
}