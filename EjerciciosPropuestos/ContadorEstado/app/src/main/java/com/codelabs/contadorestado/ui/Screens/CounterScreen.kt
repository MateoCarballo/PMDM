package com.codelabs.contadorestado.ui.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.codelabs.contadorestado.ui.State.ContadorViewModel


@Composable
fun CounterScreen(
    estado: ContadorViewModel,
    rutaUltimaPantalla : () -> Unit
){
    Column (
//        modifier = Modifier
//            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        TarjetaContador1(estado = estado)
        Spacer(modifier = Modifier.size(16.dp))
        TarjetaContador2(estado = estado)
        Button(
            onClick = {rutaUltimaPantalla}
        ) {
          Text(text = "Mostrar Total")
        }
    }
}


@Composable
fun TarjetaContador1(
    estado : ContadorViewModel,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    )
    {
        Text(
            modifier = Modifier
                .padding(10.dp) ,
            text = "Aumento contador 1: ",
        )
        TextField(
            //Aqui podria poner comillas simple y pista
            value = estado.incrementoContador1.toString(),
            onValueChange = {
                estado.cambiarIncremento1(Integer.parseInt(it))
            },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .width(10.dp)
                .padding(10.dp)
                .background(Color.Blue)
        )

        IconButton(
            onClick = {estado.incrementarContador1()}
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Aumentar contador",)
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        IconButton(
            onClick = { estado.resetearContadores()}
        ) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Borrar contador",)
        }
    }
}
@Composable
fun TarjetaContador2(
    estado : ContadorViewModel,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        )
    {
        Text(
            modifier = Modifier
                .padding(10.dp) ,
            text = "Aumento contador 2 : ",
        )
        TextField(
            //Aqui podria poner comillas simple y pista
            value = estado.incrementoContador2.toString(),
            onValueChange = {
                estado.cambiarIncremento2(Integer.parseInt(it))
            },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .width(10.dp)
                .padding(10.dp)
        )


        IconButton(
            onClick = {estado.incrementarContador2()}
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Aumentar contador",)
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        IconButton(
            onClick = { estado.resetearContadores()}
        ) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "Borrar contador",)
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CounterScreenPreview2(){
    CounterScreen(viewModel(),{})
}