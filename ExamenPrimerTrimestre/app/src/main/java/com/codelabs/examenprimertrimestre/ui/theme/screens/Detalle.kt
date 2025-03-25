package com.codelabs.examenprimertrimestre.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.examenprimertrimestre.data.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detalle(
    navigateBack: () -> Unit,
    productName: String,
    productPrice: String,
    productQuantity: String,
    toHomeScreen: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detalle", fontSize = 24.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navigateBack()
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
            Text(
                text = "Nombre del producto:  $productName" ,
                fontSize = 20.sp, // Ajusta el tamaño de la fuente
                fontWeight = FontWeight.Bold, // Pone el texto en negrita
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) // Si prefieres mantener el estilo base de bodyLarge pero con negrita
            )

            Text(
                text = "Precio unitario: $productPrice",
                fontSize = 20.sp, // Ajusta el tamaño de la fuente
                fontWeight = FontWeight.Bold, // Pone el texto en negrita
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) // Si prefieres mantener el estilo base de bodyLarge pero con negrita
            )

            Text(
                text = "Cantidad seleccionada : $productQuantity",
                fontSize = 20.sp, // Ajusta el tamaño de la fuente
                fontWeight = FontWeight.Bold, // Pone el texto en negrita
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold) // Si prefieres mantener el estilo base de bodyLarge pero con negrita
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrevieDetalle() {
    Detalle(
        navigateBack= {},
        productName = "Product name",
        productPrice = "10.99",
        productQuantity = "14",
        toHomeScreen = {})
}