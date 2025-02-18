package com.codelabs.contadoresestadonavegacion.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    toCounterScreen : () ->Unit,
    generarContadores : (counters : Int) -> Unit,
){
    var campoTextField : String = "3"
    //PANTALLA EN GENERAL
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        //Tarjeta del titulo
        Column (
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Contadores Estado-Navegación",
                fontSize = 24.sp,
            )
            Spacer(modifier = Modifier
                .padding(15.dp)
                .background(MaterialTheme.colorScheme.onPrimaryContainer))
            Text(
                text = "Pulsa el boton para comenzar los contadores",
                fontSize = 12.sp,
            )
        }
        Spacer(modifier = Modifier
            .padding(15.dp)
            .background(MaterialTheme.colorScheme.onPrimaryContainer))

        //Tatjeta del numero de contadores
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        ) {
            Text(modifier = Modifier
                .padding(16.dp),
                text = "Numero de \n contaddores"
            )
            TextField(
                modifier = Modifier
                    .size(40.dp),
                value = "",
                onValueChange = {
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number)
            )
        }
        Spacer(modifier = Modifier
            .padding(15.dp)
            .background(MaterialTheme.colorScheme.onPrimaryContainer))

        //Boton de comenzar para navegar a contadores
            Row (
                modifier = Modifier
                    .border(2.dp, Color.White, RoundedCornerShape(16.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        toCounterScreen()
                        generarContadores(Integer.parseInt(campoTextField))
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp),
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Boton comenzar",
                        )
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Comenzar")
                }

            }
    }
}



@Composable
@Preview(showBackground = true)
fun OnlyHomeScreenPreview(){
    HomeScreen({},{})
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview(){
    HomeScreen({},{})
}