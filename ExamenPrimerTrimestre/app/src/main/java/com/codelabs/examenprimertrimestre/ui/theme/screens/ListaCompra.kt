package com.codelabs.examenprimertrimestre.ui.theme.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    val listState = listScreenVM.state.collectAsState()
    LaunchedEffect(Unit) {
        listScreenVM.updateTotalPrice()
        listScreenVM.updateTotalQuantity()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lista compra", fontSize = 24.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            //TODO navegar a la anterior
                        }
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                //TODO accion de pulsar boton flotante
                     },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    contentDescription = "Añadir producto"
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                )
                {
                    Text(
                        text = "Total",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Cantidad  " + listState.value.totalItems,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Precio " + listState.value.totalPrice,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
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
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(250.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,

                    ) {
                    Text("Añadir nuevo item con precio", fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    // Linea para introducir nombre del item
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Nombre:",
                            modifier = Modifier.width(80.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        OutlinedTextField(
                            value = listState.value.newItemName,
                            onValueChange = {
                                listScreenVM.changeItemName(it)
                                listScreenVM.isEnabledAddButton()
                            },
                            placeholder = { Text("Patatas") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    // Linea para introducir la cantidad de elementos
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Precio:",
                            modifier = Modifier.width(80.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        OutlinedTextField(
                            value = listScreenVM.state.value.newItemPrice,
                            onValueChange = {
                                listScreenVM.changePriceValue(it)
                                listScreenVM.isEnabledAddButton()
                            },
                            placeholder = { Text("7,50") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                //TODO
                            },
                        ) {
                            Text(
                                text = "Generar aleatorio",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Generar aleatorio"
                            )
                        }
                        Button(
                            onClick = {
                                listScreenVM.addNewProduct()
                            },
                            enabled = listState.value.enableAddButton
                        ) {
                            Text(
                                text = "Añadir",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(listState.value.addedProducts.size) { index ->
                    ItemLista(
                        product = listState.value.addedProducts[index],
                        increaseUnit = {productName -> listScreenVM.increaseProduct(productName)},
                        decreaseUnit = {productName -> listScreenVM.decreaseProduct(productName)},
                        deleteProduct = {productName -> listScreenVM.deleteProduct(productName)},
                        toDetailScreen = toDetailScreen,
                    )
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
    deleteProduct: (String) -> Unit,
    toDetailScreen: () -> Unit,
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
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
                    decreaseUnit(product.name)
                }
            ) {
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
                    increaseUnit(product.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "increase"
                )
            }
            IconButton(
                onClick = {
                    //TODO navegar a detalles del item
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "info"
                )
            }

            IconButton(
                onClick = {
                    deleteProduct(product.name)
                    Toast.makeText(context,"Has eliminado: ${product.name} de la lista", Toast.LENGTH_SHORT).show()
                }
            ) {
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
fun PreviewItemLista() {
    ItemLista(
        product = Product(name = "Nombre", quantity = 10, price = 10.50),
        {},
        {},
        {},
        {},
    )
}