package com.codelabs.contadorestado.ui.screens


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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.contadorestado.ui.state.CounterScreenViewModel

@Composable
fun CounterScreen(
    toResultScreen : (Int) -> Unit
){
    val contadorViewModel: CounterScreenViewModel = viewModel()
    val counterState = contadorViewModel.state.collectAsState().value
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        TarjetaContador1(
            valorIncremento = counterState.incrementoContador1,
            valorTotal = counterState.acumuladoContador1,
            //TODO preguntar DANI este it se refiere,
            // al propio elemento que lo use ?
            cambiarIncrementoContador1 = {contadorViewModel.cambiarIncremento(it)},
            sumar = {contadorViewModel.incrementarContador()},
            vaciar = {contadorViewModel.resetearContador()},
            )
        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = {
                toResultScreen(contadorViewModel.devolverTotal())
            }
        ) {
          Text(text = "Mostrar Total")
        }
    }
}


@Composable
fun TarjetaContador1(
    valorIncremento : Int,
    valorTotal: Int,
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
       Column (
           modifier = Modifier,

       ){
           Text(
               modifier = Modifier
                   .padding(10.dp) ,
               text = "Aumento contador 1 :\n $valorIncremento",
               fontSize = MaterialTheme.typography.bodyLarge.fontSize,
               textAlign = TextAlign.Center,
               // VALE esto text = "Aumento contador 1: $valorTextField",
           )
           Text(
               modifier = Modifier
                   .padding(10.dp) ,
               text = "Valor total del contador:\n $valorTotal",
               fontSize = MaterialTheme.typography.bodyLarge.fontSize,
               color = Color.Red,
               textAlign = TextAlign.Center,
               // VALE esto text = "Aumento contador 1: $valorTextField",
           )
       }
        TextField(
            //Aqui podria poner comillas simple y pista
            value = valorIncremento.toString(),
            onValueChange = {
                if (it.isNotEmpty() && it.isNotBlank()){
                    cambiarIncrementoContador1(it.toInt())
                }
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
    CounterScreen {}
}