package com.codelabs.examenprimertrimestre.ui.theme.state

import com.codelabs.examenprimertrimestre.data.Product

data class ListState(
    val newItemName: String = "",
    val newItemPrice: String = "",
    val totalPrice: Double = 0.0,
    val totalItems: Int = 0,
    val enableAddButton: Boolean = false,
    //TODO Aqui quiero que por defecto traiga los datos de la DB
    val addedProducts: List<Product> = listOf(),
    val openDialogDelete: Boolean = false,
    val openDialogInsert: Boolean = false,
)
