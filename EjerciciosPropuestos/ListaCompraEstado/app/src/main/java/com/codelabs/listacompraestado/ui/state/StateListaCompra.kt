package com.codelabs.listacompraestado.ui.state

import android.content.ClipData.Item
import com.codelabs.listacompraestado.ui.data.ItemCompra

data class StateListaCompra (
    val mostrarDialogo: Boolean = false,
    val nombreItem: String = "",
    val precioItem: String = "",
    val cantidadItem: String = "",
    val lista : List<ItemCompra> = listOf(
        ItemCompra(
            nombre = "Patatas",
            precio = "3,25",
            cantidad = "2"
        ),
        ItemCompra(
            nombre = "Tomates",
            precio = "1,50",
            cantidad = "5"
        ),
        ItemCompra(
            nombre = "Leche",
            precio = "0,99",
            cantidad = "3"
        ),
        ItemCompra(
            nombre = "Pan",
            precio = "1,20",
            cantidad = "1"
        ),
        ItemCompra(
            nombre = "Arroz",
            precio = "2,75",
            cantidad = "2"
        ),
        ItemCompra(
            nombre = "Huevos",
            precio = "2,00",
            cantidad = "12"
        )),
)