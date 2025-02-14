package com.example.contador3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    var valorTextField1 by remember { mutableStateOf("1") }
    var valorTextField2 by remember { mutableStateOf("1") }

    TarjetaContador(contador1,
        valorTextField1,
        cambiaTexto = { valorTextField1  = it },
        sumar = { contador1 += Integer.parseInt(valorTextField1) },
        resetear = {contador1 = 0})
    Spacer(modifier = Modifier.padding(16.dp))
    TarjetaContador(contador2, valorTextField2,
        {valorTextField2 = it},
        { contador1 += Integer.parseInt(valorTextField2) },{contador2=0})
    Spacer(modifier = Modifier.padding(16.dp))
    ContadorConjunto(contador1,contador2)
}

@Composable
fun TarjetaContador(
    contador : Int,
    text : String,
    cambiaTexto: (String) -> Unit,
    sumar : () -> Unit,
    resetear: () -> Unit
){

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Numero de unidades aumentadas \n por cada pulsación"
            )
            TextField(
                value = text,
                onValueChange = {
                   cambiaTexto(it)
                },
                textStyle = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .width(60.dp)
                    .padding(10.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                IconButton(
                    onClick = sumar
                ) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Aumentar contador",)
                }
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar contador",
                    modifier = Modifier
                        .clickable {
                            sumar
                        }
                        .background(MaterialTheme.colorScheme.primaryContainer),

                    )
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar contador",
                    modifier = Modifier
                        .clickable {
                            resetear
                        }
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .size(30.dp)
                        .alpha(0.75f)
                    )
            }

        }
        Text(
            modifier = Modifier.padding(30.dp),
            text = "El valor del contador es $contador",
            fontSize = 24.sp,
        )
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