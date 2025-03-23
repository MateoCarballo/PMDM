package com.codelabs.examenprimertrimestre.ui.theme.state

import com.codelabs.examenprimertrimestre.data.Product
import com.codelabs.examenprimertrimestre.data.getFakeProducts

data class ListState(
    val newItemName: String = "",
    val newItemPrice: String = "",
    val totalPrice: Double = 0.0,
    val totalItems: Int = 0,
    val enableAddButton: Boolean = false,
    val addedProducts: List<Product> = getFakeProducts(),
)