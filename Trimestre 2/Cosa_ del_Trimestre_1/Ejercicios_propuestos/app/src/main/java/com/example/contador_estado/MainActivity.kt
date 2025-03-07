package com.example.contador_estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContador()
        }
    }
}

@Composable
fun AppContador(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ContadorApartado2()
    }
}

@Composable
fun ContadorApartado2(){
    var contador1 by remember { mutableIntStateOf(0) }
    var contador2 by remember { mutableIntStateOf(0) }
    TarjetaContador(contador1, {contador1++},{contador1 = 0})
    Spacer(modifier = Modifier.padding(16.dp))
    TarjetaContador(contador2,  {contador2++},{contador2 = 0})
    Spacer(modifier = Modifier.padding(16.dp))
    ContadorConjunto(contador1,contador2)
}

@Composable
fun TarjetaContador( contador : Int, sumar :() -> Unit, resetear: () -> Unit){

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Valor del contador -> $contador", fontSize = 24.sp)
        Button(onClick =  sumar ){
            Text(text = "Incrementar")
        }
        Button(onClick = resetear) {
            Column {
                Text(text = "Reiniciar")
                Text(text = "Contador")
            }
        }
    }
}

@Composable
fun ContadorConjunto(contador1: Int, contador2: Int){
    Text(
        text = "La suma es ${contador1 + contador2}",
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.primaryContainer)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContadorPreview() {
    AppContador()
}