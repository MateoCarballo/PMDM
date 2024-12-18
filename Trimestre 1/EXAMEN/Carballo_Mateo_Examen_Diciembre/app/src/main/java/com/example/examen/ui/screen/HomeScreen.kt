package com.example.examen.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
    goToPrincipalScreen: () -> Unit,
    ){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "Bienvenidos a una nueva versión de la lista de la compra",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Pulsa en 'Acceder' para comenzar a añadir tus productos" ,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = goToPrincipalScreen) {
            Row {
                Text(
                    text = "Acceder",
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Icon(imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Start Button")
            }
        }
    }
}
