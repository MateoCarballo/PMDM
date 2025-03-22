package com.codelabs.examenprimertrimestre.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codelabs.examenprimertrimestre.data.Product
import com.codelabs.examenprimertrimestre.ui.theme.state.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompra(
    toDetailScreen: () -> Unit,
    listScreenVM: ListViewModel = viewModel()
) {
    val state = listScreenVM.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lista compra", fontSize = 24.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { /* Acción de retroceso */ }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Text(
                    text = "Total: $10.00",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        // Contenido principal con padding respetando el Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), // Respeta TopBar y BottomBar
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Tu lista de compras", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp)) // Corregido el modificador
            Button(onClick = toDetailScreen) {
                Text("Ir a detalles")
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.value.addedProducts.size) { index ->
                    ItemLista(
                        product = state.value.addedProducts[index],
                        increaseUnit = {},
                        decreaseUnit = {},
                        toDetailScreen = toDetailScreen)
                }
            }
        }
    }
}

@Composable
fun ItemLista(
    product: Product,
    increaseUnit: (String) -> Unit,
    decreaseUnit: (String) -> Unit,
    toDetailScreen: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(
                text = product.name
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = {
                    //TODO decrementar
                }
            ){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "decrease"
                )
            }

            Text(
                text = product.quantity.toString()
            )

            IconButton(
                onClick = {
                    //TODO incrementar
                }
            ){
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "increase"
                )
            }
            IconButton(
                onClick = {
                    //TODO navegar a detalles del item
                }
            ){
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "info"
                )
            }

            IconButton(
                onClick = {
                    //TODO eliminar item
                }
            ){
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete"
                )
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListaCompra() {
    ListaCompra({})
}

@Preview(showBackground = true)
@Composable
fun PreviewItemLista(){
    ItemLista(
        product = Product(name = "Nombre", quantity = 10, price = 10.50),
        {},
        {},
        {},
    )
}

