package com.codelabs.examenprimertrimestre.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Detalle(
    toHomeScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Pantalla Detalle")
        Button(
            onClick = { toHomeScreen() }
        ) {
            Text("Navegar")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevieDetalle() {
    Detalle({})
}