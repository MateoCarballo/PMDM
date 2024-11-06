package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Container(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Container(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ){
        Row (modifier = modifier){
            TextFieldEntradaLista(modifier)
            ButtonAddItem(modifier)
        }
        LazyColumnList()
    }
}

@Composable
fun TextFieldEntradaLista(modifier: Modifier){
    var campo by remember {
        mutableStateOf("")
    }

    TextField(
        value = campo,
        onValueChange = {campo = it},
        label = {Text ("Elemento")},
        placeholder = {Text ("Añade un nuevo elemento a la lista")}
    )
}

@Composable
fun ButtonAddItem(
    modifier: Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = {},
        content = {
            Icon(Icons.Default.Add, contentDescription = "Add button")
        }
    )
}

@Composable
fun LazyColumnList() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {
        Container()
    }
}