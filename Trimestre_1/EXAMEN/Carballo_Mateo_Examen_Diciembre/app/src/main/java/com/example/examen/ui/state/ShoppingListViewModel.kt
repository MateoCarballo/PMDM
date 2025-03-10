package com.example.examen.ui.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ShoppingListViewModel : ViewModel() {
/*

    private val _uiState = MutableStateFlow(ShoppingListUiState())
    val uiState: StateFlow<ShoppingListUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = ShoppingListUiState(getFakeProducts().toMutableStateList())
    }

    fun remove(item: Product) {
        _uiState.value = _uiState.value.copy(list = _uiState.value.list.toMutableStateList().apply { remove(item) })

    }

    fun add(name: String) = if (_uiState.value.list.find { it.name == name } == null) {
        _uiState.value = _uiState.value.copy(list = _uiState.value.list.toMutableStateList().apply {//TODO añadir nuevo producto})
        true
    } else {
        false
    }

    fun changingNewProduct(newProduct: Product) {
        _uiState.value = _uiState.value.copy(newProduct = newProduct)
    }
 */
}