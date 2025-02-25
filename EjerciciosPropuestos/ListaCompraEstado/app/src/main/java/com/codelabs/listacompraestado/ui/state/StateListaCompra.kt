package com.codelabs.listacompraestado.ui.state

import android.content.ClipData.Item
import com.codelabs.listacompraestado.ui.data.ItemCompra

data class StateListaCompra (
    val mostrarDialogoAñadirItem: Boolean = false,
    val mostrarDialogoInfoItem: Boolean = false,
    val itemMuestraInfo: Int = -1,
    val lista: List<ItemCompra> = listOf(
        ItemCompra(nombre = "Patatas", precio = "3,25", cantidad = "2"),
        ItemCompra(nombre = "Tomates", precio = "1,50", cantidad = "5"),
        ItemCompra(nombre = "Leche", precio = "0,99", cantidad = "3"),
        ItemCompra(nombre = "Pan", precio = "1,20", cantidad = "1"),
        ItemCompra(nombre = "Arroz", precio = "2,75", cantidad = "2"),
        ItemCompra(nombre = "Huevos", precio = "2,00", cantidad = "12"),
        ItemCompra(nombre = "Manzanas", precio = "1,99", cantidad = "6"),
        ItemCompra(nombre = "Peras", precio = "2,10", cantidad = "4"),
        ItemCompra(nombre = "Naranjas", precio = "2,50", cantidad = "5"),
        ItemCompra(nombre = "Plátanos", precio = "1,75", cantidad = "6"),
        ItemCompra(nombre = "Cebollas", precio = "1,30", cantidad = "3"),
        ItemCompra(nombre = "Zanahorias", precio = "1,20", cantidad = "7"),
        ItemCompra(nombre = "Pepinos", precio = "1,50", cantidad = "2"),
        ItemCompra(nombre = "Pimientos", precio = "2,80", cantidad = "3"),
        ItemCompra(nombre = "Aceite de oliva", precio = "5,99", cantidad = "1"),
        ItemCompra(nombre = "Vinagre", precio = "1,80", cantidad = "1"),
        ItemCompra(nombre = "Azúcar", precio = "1,50", cantidad = "1"),
        ItemCompra(nombre = "Sal", precio = "0,99", cantidad = "1"),
        ItemCompra(nombre = "Harina", precio = "1,25", cantidad = "2"),
        ItemCompra(nombre = "Pasta", precio = "2,30", cantidad = "3"),
        ItemCompra(nombre = "Galletas", precio = "2,60", cantidad = "2"),
        ItemCompra(nombre = "Café", precio = "4,50", cantidad = "1"),
        ItemCompra(nombre = "Té", precio = "3,20", cantidad = "1"),
        ItemCompra(nombre = "Chocolate", precio = "2,75", cantidad = "1"),
        ItemCompra(nombre = "Queso", precio = "3,99", cantidad = "1"),
        ItemCompra(nombre = "Yogur", precio = "2,50", cantidad = "6")
    ),
)