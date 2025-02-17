package com.codelabs.contadorestado.ui.Screens


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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.codelabs.contadorestado.ui.State.ContadorViewModel


@Composable
fun CounterScreen(estado: ContadorViewModel,nav : NavController){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        TarjetaContador1(estado = estado)
        TarjetaContador2(estado = estado)
        Button(
            onClick = {nav.navigate()}
        ) { }
    }
}


@Composable
fun TarjetaContador1(
    estado : ContadorViewModel,
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
                //Aqui podria poner comillas simple y pista
                value = estado.incrementoContador1.toString(),
                onValueChange = {
                    estado.cambiarIncremento1(Integer.parseInt(it))
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
                    onClick = {estado.incrementarContador1()}
                ) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Aumentar contador",)
                }
                /*
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar contador",
                    modifier = Modifier
                        .clickable {
                            sumar
                        }
                        .background(MaterialTheme.colorScheme.primaryContainer),

                    )
                 */
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar contador",
                    modifier = Modifier
                        .clickable {
                            estado.resetearContadores()
                        }
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .size(30.dp)
                        .alpha(0.75f)
                )
            }

        }
    }
}

@Composable
fun TarjetaContador2(
    estado : ContadorViewModel,
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
                value = estado.incrementoContador2.toString(),
                onValueChange = {
                    estado.cambiarIncremento2(Integer.parseInt(it))
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
                    onClick = { estado.incrementoContador2 }
                ) {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Aumentar contador",)
                }
                /*
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar contador",
                    modifier = Modifier
                        .clickable {
                            sumar
                        }
                        .background(MaterialTheme.colorScheme.primaryContainer),

                    )
                 */
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar contador",
                    modifier = Modifier
                        .clickable {
                            estado.resetearContadores()
                        }
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .size(30.dp)
                        .alpha(0.75f)
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CounterScreenPreview(){
    CounterScreen(viewModel())
}