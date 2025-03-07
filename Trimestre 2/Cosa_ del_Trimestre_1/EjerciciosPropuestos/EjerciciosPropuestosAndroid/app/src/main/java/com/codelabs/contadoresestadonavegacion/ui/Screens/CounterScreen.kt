package com.codelabs.contadoresestadonavegacion.ui.Screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CounterScreen(
    toResultScreen : () -> Unit,
    numeroContadores : Int,
    actualizarIncrementos: (Int) -> String,
    actualizarTotales: (Int) -> Unit,
){
    //Lazy column si pasamos como parametro cuantos contadores vamos a crear
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        /*
        METODO PRINGADOS

        for (i in 1..numeroContadores) {
            Counter(
                numeroContador = i,
                actualizarIncremento = actualizarIncrementos,
                actualizarTotales = actualizarTotales,
            )
        }
         */

        LazyColumn(
            modifier = Modifier.weight(1f), // Para que ocupe todo el espacio disponible
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,// Espaciado entre contadores
        ) {
            items((1..numeroContadores).toList()) { i ->
                Counter(
                    numeroContador = i,
                    actualizarIncremento = actualizarIncrementos,
                    actualizarTotales = actualizarTotales,
                )
            }
        }
        BotonEnviar(toResultScreen)
    }
}

@Composable
fun Counter (
    numeroContador: Int = 1,
    actualizarIncremento: (Int) -> String,
    actualizarTotales: (Int) -> Unit,
){

    var valorIncremento : String = ""
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = "Contador $numeroContador",
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        )
        Row (
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Incremento: $valorIncremento"
            )
            Spacer(
                modifier = Modifier
                    .size(8.dp),
            )
            //No se como hacer que llame al metodo que modifica el incremento del contador
            //pasandole el numero de contador menos 1 para saber cual es en el indice
            TextField(
                modifier = Modifier
                    .size(32.dp),
                value = "",
                //Quiero que si realizado la modificacion me vuelva a poner a cero
                //El valor del textField(en blanco) y que me actualice la eqtiqeuta
                onValueChange = {
                    valorIncremento = it
                }
            )
            IconButton(
                //Cuando pulso el boton mas de cada contador
                //Actualizo el incremento para el contador y
                // los totales de cada contador
                onClick = {
                    actualizarIncremento(Integer.parseInt(valorIncremento))
                    actualizarTotales(numeroContador)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar contador"
                )
            }
        }
        Row (
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = "Total Contador:  $valorIncremento"
            )

        }
    }
}

@Composable
fun BotonEnviar(
    toResultScreen: () -> Unit,
){
    Button(
        modifier = Modifier
            .padding(16.dp),
        onClick = toResultScreen
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp),
            imageVector = Icons.AutoMirrored.Default.Send,
            contentDescription = "Realizar la suma"
        )
        Text(text = "Sumar contadores")
    }
}

@Composable
@Preview(showBackground = true)
fun OnlyCounterScreenPreview(){
    CounterScreen({},20,{""},{})
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CounterScreenPreview(){
    CounterScreen({},20,{""},{})
}