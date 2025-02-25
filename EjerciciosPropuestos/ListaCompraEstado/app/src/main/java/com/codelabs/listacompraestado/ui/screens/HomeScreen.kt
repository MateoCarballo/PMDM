package com.codelabs.listacompraestado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    toListScreen: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Bienvenido a Lista de Compra",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = toListScreen,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(12.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Ir a la lista de compra",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Comenzar",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeScreenPreview() {
    HomeScreen({})
}
