package com.codelabs.contadorestado.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ResultScreen(
    toHomeScreen : () -> Unit,
    resultado: Int,
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier
                .padding(30.dp),
            //TODO Como paso de la pantalla anterior el resultado suma de los contadores ruta/parametro
            text = "El valor del contador es $resultado",
            fontSize = 24.sp,
        )
        Button(
            onClick = {toHomeScreen()}
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Navegar a home",
            )
            Text("Navegar a HomeScreen")
        }
    }
}