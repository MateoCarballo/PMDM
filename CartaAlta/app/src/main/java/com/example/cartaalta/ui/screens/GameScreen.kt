package com.example.cartaalta.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController) {
    // Estado para las cartas de los jugadores, inicializados como null
    var player1Card by remember { mutableStateOf<Int?>(null) }
    var player2Card by remember { mutableStateOf<Int?>(null) }

    // Estado para habilitar o deshabilitar el botón "Terminar juego"
    var gameOverEnabled by remember { mutableStateOf(false) }

    // Barra superior (TopAppBar)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(text = "Juego Carta Alta") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para que el Jugador 1 robe su carta
        Button(onClick = {
            player1Card = Random.nextInt(1, 14) // Genera un número aleatorio entre 1 y 13
        }, enabled = player1Card == null) {  // Deshabilita el botón si ya ha robado la carta
            Text(text = "Jugador 1 roba carta")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Mostrar la carta del Jugador 1 si ya la ha robado
        if (player1Card != null) {
            Text("Jugador 1 sacó: $player1Card")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para que el Jugador 2 robe su carta
        Button(onClick = {
            player2Card = Random.nextInt(1, 14) // Genera un número aleatorio entre 1 y 13
        }, enabled = player2Card == null) {  // Deshabilita el botón si ya ha robado la carta
            Text(text = "Jugador 2 roba carta")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Mostrar la carta del Jugador 2 si ya la ha robado
        if (player2Card != null) {
            Text("Jugador 2 sacó: $player2Card")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para terminar el juego
        Button(
            onClick = {
                // Determinar el ganador
                val winner = when {
                    player1Card == player2Card -> "Empate"
                    player1Card!! > player2Card!! -> "Jugador 1"
                    else -> "Jugador 2"
                }
                // Navegar a la pantalla de GameOver pasando el ganador
                navController.navigate("gameOver/$winner")
            },
            enabled = player1Card != null && player2Card != null // Se habilita solo si ambos jugadores han robado su carta
        ) {
            Text("Terminar juego")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen(navController = rememberNavController())
}
