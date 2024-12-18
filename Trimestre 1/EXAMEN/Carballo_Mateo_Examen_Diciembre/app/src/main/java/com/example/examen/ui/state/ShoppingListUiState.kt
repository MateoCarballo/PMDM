package com.example.examen.ui.state

import  com.example.examen.model.Product

data class ShoppingListUiState(
    val list: List<Product> = emptyList(),
    val newProduct: Product,
    val isSomethingChecked: Boolean = false
)



