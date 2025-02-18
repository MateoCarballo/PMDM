package com.codelabs.contadoresestadonavegacion.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    toHomeScreen: () -> Unit,
    sumaContadores: Int
){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TextoSumaContadores(sumaContadores)
        BotonVolverHomeScreen(toHomeScreen)
    }
}

@Composable
fun TextoSumaContadores(
    //Aqui la idea es que llegue la suma desde la otra pantalla counterSCreen
    //sumaContadores: () -> Unit
    suma : Int
){
    Text(
        modifier = Modifier,
        text = "La suma de los contadores es $suma",
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
    )
}

@Composable
fun BotonVolverHomeScreen(
    restartApp: () -> Unit
){
    Button(
        modifier = Modifier
            .padding(16.dp),
        onClick = restartApp
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp),
            imageVector = Icons.Default.Refresh,
            contentDescription = "Volver a home"
        )
        Text(text = "Volver a Home")
    }
}

@Composable
@Preview(showBackground = true)
fun SoloResultScreenPreview(){
    ResultScreen({},1)
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ResultScreenPreview(){
    ResultScreen({},1)
}