package com.codelabs.listacompraestado.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelabs.listacompraestado.ui.data.ItemCompra
import com.codelabs.listacompraestado.ui.state.StateListaCompra

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Mi App",
                            color = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color(0xFF6200EE), // Color de fondo de la TopAppBar
                    Color.White, // Color del texto del título
                    Color.White // Color del icono de navegación (flecha)
                )
            )
        },
        //TODO pregunta 1
        content = {
            BodyContent(modifier = Modifier.padding(it))
        }

    )
}

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    listaElementos: List<ItemCompra> = StateListaCompra().lista
) {
    LazyColumn(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp) // Este padding se asegura de que no se pegue a la TopAppBar
            .fillMaxSize(),  // Asegura que ocupe todo el espacio disponible,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(listaElementos.size) { index ->
            TarjetaItem(
                nombre = listaElementos[index].nombre,
                precio = listaElementos[index].precio,
                cantidad = listaElementos[index].cantidad
            )
        }

    }
}

@Composable
fun TarjetaItem(
    nombre: String,
    precio: String,
    cantidad: String,
) {
    Row (
        modifier = Modifier
            .padding(16.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp),
            )
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ){
        Column(
            //Modificadores de la columna de datos item
        )
        {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = nombre,
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )
            Text(
                text = "Precio: $precio",
                fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
            )
            Text(
                text = "Cantidad: $cantidad"
            )

        }
        Column (

        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar"
                )
            }
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewListScreen() {
    ListScreen()
}

@Composable
@Preview(showBackground = true)
fun PreviewBodyContent() {
    BodyContent()
}
