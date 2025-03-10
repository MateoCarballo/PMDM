package com.example.examen.ui.screen


import com.example.examen.data.products
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen.data.products
import com.example.examen.model.Product
import com.example.examen.model.getFakeProducts
import org.w3c.dom.Text

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    goToDetailScreen: () -> Unit,   //TODO esto se lo pasamos al icono de informacion para ir a la ventana de detalles
    restartApp: () -> Unit          // TODO Volvemos a la pantalla home com '.popBackStack' para eliminar la pila de ventanas
){
    var products by remember { mutableStateOf(getFakeProducts()) }
    // Definidos top y bottom
    Scaffold (
        modifier = Modifier.padding(16.dp),
        topBar = {
            TopAppBar(
                title = {
                    PersonalTopAppBarRow(restartApp)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        },
        bottomBar = {
            BottomAppBar (
                modifier = Modifier,
                containerColor = BottomAppBarDefaults.containerColor,
                contentColor = contentColorFor(containerColor),
                tonalElevation = BottomAppBarDefaults.ContainerElevation,
                contentPadding = BottomAppBarDefaults.ContentPadding,
            ){
              Row(
                  horizontalArrangement = Arrangement.SpaceAround,
              ){
                  Text(text = "Total",
                      color = MaterialTheme.colorScheme.primary,
                      style = MaterialTheme.typography.bodySmall,
                      )
                  Spacer(Modifier.padding(8.dp))
                  Text(text = "Cantidad",
                      color = MaterialTheme.colorScheme.primary,
                      style = MaterialTheme.typography.bodySmall,)
                  Spacer(Modifier.padding(8.dp))
                  Text(text = "Precio",
                      color = MaterialTheme.colorScheme.primary,
                      style = MaterialTheme.typography.bodySmall,)

              }
            }
        }
    ){
        //Contenido principal
        Column (
            modifier = Modifier
                //.padding(it)
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ){
            AddProductRow()
            ListOfAleatoryItems(
                products,
                goToDetailScreen
            )
        }
    }

}

@Composable
fun ListOfAleatoryItems(
    products: List<Product>,
    navigate: () -> Unit
) {
    LazyColumn {
        items(products.size) {
            val product = products.elementAt(it)
            ShoppingListItem(
                product = product,
                goToDetailScreen = navigate,
            )
        }
    }
}

@Composable
fun ShoppingListItem(
    product: Product,
    goToDetailScreen: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .background(color = Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    )
    {
        Row {
            Text(
                text = product.name
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = {} ) {
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Delete one item")
            }
            Text(
                text = "${product.price}"
            )
            IconButton(onClick = {} ) {
                Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "Add one item")
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = goToDetailScreen
             ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "Details of the product")
            }
            
            IconButton(onClick = {} ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete the item")
            }
        }
    }
}

@Composable
fun PersonalTopAppBarRow(restartApp: () -> Unit) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        OutlinedIconButton (
            onClick = restartApp,
            enabled = true,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Shopping List",
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun AddProductRow() {
    val value = "valor"
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ){
        Column {
            TextField(
                value = value,
                // placeholder = Text( text = "Manzanas..."),
                onValueChange = { },
                singleLine = true,
            )
            TextField(
                value = value,
                onValueChange = { },
                singleLine = true,
            )
        }
        IconButton(onClick = {} )
        {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add item"
            )
        }
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Button(onClick = {}) {
        Row() {
            Text(
                text = "Aleatorio",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Start,
                
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Icon(imageVector = Icons.Filled.Add,
                contentDescription = "Add aleatory item")
        }
    }
}

@Preview
@Composable
fun previeewew(){
    PrincipalScreen({},{})
}

