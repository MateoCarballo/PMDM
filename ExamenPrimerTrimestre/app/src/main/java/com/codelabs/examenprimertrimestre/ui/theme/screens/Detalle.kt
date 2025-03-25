package com.codelabs.examenprimertrimestre.ui.theme.screens

import android.sax.Element
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            verticalArrangement = Arrangement.Top
        ) {
            Card (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ){
                Column(
                    modifier = Modifier.padding(16.dp),
                ){
                    ElementoDetalle("Nombre",productName)
                    ElementoDetalle("Precio",productPrice)
                    ElementoDetalle("Cantidad",productQuantity)
                    ElementoDetalle("Precio total elementos",
                        (productQuantity.toInt() * productPrice.toDouble()).toString()
                    )
                }
            }
        }
    }
}

@Composable
fun ElementoDetalle(
    campoPersonalizado:String,
    valorPersonalizado:String,
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),  // Un poco de espacio vertical entre los elementos
        horizontalArrangement = Arrangement.SpaceBetween,  // Distribuye los elementos en los extremos
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(
            text = campoPersonalizado ,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = valorPersonalizado,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
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