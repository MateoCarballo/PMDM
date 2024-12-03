package com.example.cartaalta

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun GameOverScreen(navController: NavController, winner: String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Resultado del juego")
        Spacer(modifier = Modifier.height(16.dp))
        if (winner != null){
            Text(text = "El ganado es: $winner")
        } else {
            Text(text = "Ha habido un empate!")
        }
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = {navController.popBackStack("home",false)}) {
            Text(text = "Volver a Inicio")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GameOverScreenPreview() {
    GameOverScreen(navController = rememberNavController(), winner = "Jugador 1")
}
