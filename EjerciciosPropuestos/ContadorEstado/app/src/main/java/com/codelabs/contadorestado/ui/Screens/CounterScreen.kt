package com.codelabs.contadorestado.ui.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.contadorestado.ui.State.CounterState
import kotlinx.coroutines.flow.StateFlow


@Composable
fun CounterScreen(
    estadoActual: StateFlow<CounterState>,
    cambiarIncrementoContador1: () -> Unit,
    sumarContador1: () -> Unit,
    vaciarContador1: () -> Unit,
    rutaUltimaPantalla : () -> Unit,
    ){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        TarjetaContador1(
            estadoActual = estadoActual,
            cambiarIncrementoContador1 = { cambiarIncrementoContador1() },
            sumar = {sumarContador1()},
            vaciar = {vaciarContador1()},
            )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {rutaUltimaPantalla()}
        ) {
          Text(text = "Mostrar Total")
        }
    }
}


@Composable
fun TarjetaContador1(
    estadoActual: StateFlow<CounterState>,
    cambiarIncrementoContador1 : (Int) -> Unit,
    sumar : () -> Unit,
    vaciar : () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                2.dp,
                Color.Black,
                RoundedCornerShape(16.dp)
            ) ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    )
    {
        Text(
            modifier = Modifier
                .padding(10.dp) ,
            text = "Aumento contador 1 :\n" +" ${estadoActual.value.incrementoContador1}",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            textAlign = TextAlign.Center,
            // VALE esto text = "Aumento contador 1: $valorTextField",
        )
        TextField(
            //Aqui podria poner comillas simple y pista
            value = estadoActual.value.incrementoContador1.toString(),
            onValueChange = {
                if (it.isNotEmpty() || it.isNotBlank()) cambiarIncrementoContador1(it.toInt())
            },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
                .background(Color.Blue)
        )

        IconButton(
            onClick = {
                sumar()
            }
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Aumentar contador",)
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        IconButton(
            onClick = {
                vaciar()
            }
        ) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Borrar contador",)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CounterScreenPreview2(){
    CounterScreen(viewModel(),{},{},{},{})
}