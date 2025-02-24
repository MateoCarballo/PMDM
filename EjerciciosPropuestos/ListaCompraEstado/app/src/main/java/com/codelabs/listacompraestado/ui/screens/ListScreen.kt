package com.codelabs.listacompraestado.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.codelabs.listacompraestado.ui.data.ItemCompra
import com.codelabs.listacompraestado.ui.state.StateListaCompra
import com.codelabs.listacompraestado.ui.state.listaCompraViewModel
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    val vmListScreen = listaCompraViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Mi App", color = MaterialTheme.colorScheme.onPrimary)
                },
                navigationIcon = {
                    IconButton(onClick = {}) { //TODO programar volver atrás
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = Color.White
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    vmListScreen.cambiarEstadoDialogoAñadirItem()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Boton añadir items"
                )
            }
        },

        //TODO pregunta 1 no me queda claro si tengo que pasarle,
        // el viewmodel o su copia,
        // para que recomponga el estado correctamente no soy,
        // capaz de eliminar items de la lista

        /*TODO he tenido que modificarlo, antes tenia algo como esto,
           listaElementos = vmListScreen.state.collectAsState().value.lista,
           y ahora al hacerlo como una variable dentro del composable si que recompone bien
        */

        content = {
            BodyContent(
                modifier = Modifier.padding(it),
                listaElementos = vmListScreen.state, // .collectAsState().value.lista
                añadirItem = { item -> vmListScreen.añadirItem(item) },
                eliminarItem = { indice -> vmListScreen.eliminarElemento(indice) },
                //DIALOGO NUEVO ITEM Y ESTADO ( SI TIENE QUE MOSTRARLO/COMPONERLO O NO)
                mostrarDialogoNuevoItem = vmListScreen.state.collectAsState().value.mostrarDialogoAñadirItem,
                cambiarEstadoDialogoNuevoItem = { vmListScreen.cambiarEstadoDialogoAñadirItem() },
                //DIALOGO INFO INTEM Y ESTADO (SI TIENE QUE COMPONERLO O NO)
                mostrarDialogoInfoItem = vmListScreen.state.collectAsState().value.mostrarDialogoInfoItem,
                cambiarEstadoDialogoInfoItem = { vmListScreen.cambiarEstadoDialogoInfoItem() },
            )
        }
    )

}

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    listaElementos: StateFlow<StateListaCompra>,
    añadirItem: (ItemCompra) -> Unit,
    eliminarItem: (Int) -> Unit,
    mostrarDialogoNuevoItem: Boolean,
    cambiarEstadoDialogoNuevoItem: () -> Unit,
    mostrarDialogoInfoItem: Boolean,
    cambiarEstadoDialogoInfoItem: () -> Unit,
) {
    val context = LocalContext.current
    val listaParaLazyColumn = listaElementos.collectAsState().value.lista

    if (mostrarDialogoNuevoItem) {
        DialogoAñadirItem(
            cambiarEstadoDialogo = { cambiarEstadoDialogoNuevoItem() },
            alConfirmar = { nombre, precio, cantidad ->
                añadirItem(ItemCompra(nombre, precio, cantidad))
            }
        )
    }

    LazyColumn(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp) // Este padding se asegura de que no se pegue a la TopAppBar
            .fillMaxSize(),  // Asegura que ocupe todo el espacio disponible,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(listaParaLazyColumn.size) { index ->
            TarjetaItem(
                item = listaParaLazyColumn[index],
                index = index,
                eliminarItem = { indice ->
                    eliminarItem(indice)
                    Toast.makeText(
                        context,
                        "Elemento ${listaParaLazyColumn[index].nombre} eliminado",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                mostrarDialogoInfoItem = mostrarDialogoInfoItem,
                cambiarEstadoDialogoInfoItem = { cambiarEstadoDialogoInfoItem() },
            )
        }

    }
}

@Composable
fun DialogoAñadirItem(
    cambiarEstadoDialogo: () -> Unit,
    alConfirmar: (String, String, String) -> Unit
) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { cambiarEstadoDialogo() },
        title = { Text("Añadir nuevo producto") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre del producto") }
                )
                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank() && cantidad.isNotBlank()) {
                        alConfirmar(nombre, precio, cantidad)
                        Toast.makeText(context, "Producto añadido", Toast.LENGTH_SHORT).show()
                        cambiarEstadoDialogo()
                    }
                }
            ) {
                Text("Añadir")
            }
        },
        dismissButton = {
            Button(onClick = { cambiarEstadoDialogo() }) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun DialogoInformacionItem(
    cambiarEstadoDialogoInfoItem: () -> Unit,
    item: ItemCompra,
) {
    Dialog(onDismissRequest = cambiarEstadoDialogoInfoItem) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Detalles del Item", style = MaterialTheme.typography.titleLarge)
                Text("Nombre: ${item.nombre}")
                Text("Precio: ${item.precio}")
                Text("Cantidad: ${item.cantidad}")

                Button(
                    onClick = cambiarEstadoDialogoInfoItem,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Cerrar")
                }
            }
        }
    }

}


@Composable
fun TarjetaItem(
    item: ItemCompra,
    index: Int,
    eliminarItem: (Int) -> Unit,
    mostrarDialogoInfoItem: Boolean,
    cambiarEstadoDialogoInfoItem: () -> Unit,
) {

    if (mostrarDialogoInfoItem) {
        DialogoInformacionItem(
            cambiarEstadoDialogoInfoItem = { cambiarEstadoDialogoInfoItem() },
            item = item,
        )
    }

    Row(
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
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = item.nombre,
            fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
        Row(

        ) {
            IconButton(
                onClick = {
                    eliminarItem(index)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar"
                )
            }
            IconButton(
                onClick = {
                    cambiarEstadoDialogoInfoItem()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDialogoAñadirItem() {
    DialogoAñadirItem(
        cambiarEstadoDialogo = {},
        alConfirmar = { _, _, _ -> }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewListScreen() {
    ListScreen()
}


