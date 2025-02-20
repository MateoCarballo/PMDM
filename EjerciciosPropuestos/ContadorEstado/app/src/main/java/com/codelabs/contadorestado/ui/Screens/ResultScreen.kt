package com.codelabs.contadorestado.ui.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.contadorestado.ui.State.CounterState


@Composable
fun ResultScreen(resultadoSuma : Int){
    Text(
        modifier = Modifier.padding(30.dp),
        text = "El valor del contador es $resultadoSuma",
        fontSize = 24.sp,
    )
}