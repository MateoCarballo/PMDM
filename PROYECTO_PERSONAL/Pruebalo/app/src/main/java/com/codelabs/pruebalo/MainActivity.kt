package com.codelabs.pruebalo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelabs.pruebalo.ui.theme.PruebaloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaloTheme {
                PantallaPrincipal()
            }
        }
    }
}


//TODO -> No entiendo porque si le paso que el modifier
// tenga 50 de padding no se aplica a todos los elementos dentro de la columna
// si arriba en el modifier que paso le digo que use el maximo del ancho los elementos
// de la Column no se me quedan centrados. Por queé pasamos un modifier si tenemos que
// especificar de todos modos cada parametro en cada uno de los elementos composable


@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Pruébalo",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(100.dp),
        )

        var campo by remember {
            mutableStateOf("")
        }

        TextField(
            modifier = modifier
                .clip(CircleShape),
            value = campo,
            onValueChange = {campo = it},
//            label = {
//                Text (
//                    text = "Elemento",
//                    textAlign = TextAlign.Center,
//                    )
//                    },
            placeholder = {
                Text (
                    textAlign = TextAlign.Center,
                    text = "Qué quieres probar ?",
                )
            }
        )

        Button(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(50.dp)
                .fillMaxWidth(),
            onClick = {
                println("Has pulsado el botón")
            },
        ) {
            Text(text = "Buscar")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PruebaloTheme {
        PantallaPrincipal(modifier = Modifier)
    }
}