package com.codelabs.examenprimertrimestre.ui.theme.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.examenprimertrimestre.data.Product
import com.codelabs.examenprimertrimestre.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListViewModel(
    //TODO porque necesitaria esto, que aparece en el video pero en mi caso me guarda datos sin usarlo para nada?
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ListState())
    val state: StateFlow<ListState> = _state.asStateFlow()

    suspend fun getProductsFromDatabase() {
        //TODO aqui intento recoger lo primero del flujo para transformarlo en una lista
        val productsFromDatabase: List<Product> = productRepository.getAllProductsStream().first()
        _state.update {
            it.copy(
                addedProducts = productsFromDatabase
            )
        }
    }

    /* COMO ESTABA CON UNA LISTA GENERADA ALEATORIAMENNTE
    fun addNewProduct() {
        if (_state.value.newItemName.isNotBlank() && _state.value.newItemName.isNotEmpty() && _state.value.newItemPrice.isNotBlank() && _state.value.newItemPrice.isNotEmpty()) {
            val price = _state.value.newItemPrice.toDoubleOrNull()
            if (price != null && price > 0) {
                val newProduct = Product(name = _state.value.newItemName, price = price)

                _state.update {
                    it.copy(
                        addedProducts = it.addedProducts + newProduct
                    )
                }
            }
        }
    }
     */

    fun addNewProduct() {
        if (_state.value.newItemName.isNotBlank() && _state.value.newItemName.isNotEmpty() && _state.value.newItemPrice.isNotBlank() && _state.value.newItemPrice.isNotEmpty()) {
            val isRepeated = _state.value.addedProducts.any { it.name == _state.value.newItemName }
            if (!isRepeated) {
                val price = _state.value.newItemPrice.toDoubleOrNull()
                if (price != null && price > 0) {
                    val newProduct = Product(name = _state.value.newItemName, price = price)
                    _state.update {
                        it.copy(
                            newItemName = "",
                            newItemPrice = "",
                            addedProducts = it.addedProducts + newProduct
                        )
                    }
                    viewModelScope.launch {
                        productRepository.insertProduct(newProduct)
                    }
                }
            }
        }
        updateTotalTotals()
    }

    fun increaseProduct(productName: String) {
        val productIndex = _state.value.addedProducts.indexOfFirst { it.name == productName }

        if (_state.value.addedProducts[productIndex].quantity <= 99) {
            val updatedProduct = _state.value.addedProducts[productIndex].copy(
                quantity = _state.value.addedProducts[productIndex].quantity + 1
            )

            val updatedList = _state.value.addedProducts.toMutableList().apply {
                this[productIndex] = updatedProduct
            }
            _state.update {
                it.copy(
                    addedProducts = updatedList
                )
            }
            // Buscamos el producto en la DB que sea igual al que estamos aucmento la cantidad
            viewModelScope.launch {
                productRepository.updateProduct(_state.value.addedProducts[productIndex])
            }
        }
        updateTotalTotals()
    }

    fun decreaseProduct(productName: String) {
        val productIndex = _state.value.addedProducts.indexOfFirst { it.name == productName }

        if (_state.value.addedProducts[productIndex].quantity > 1) {
            val updatedProduct = _state.value.addedProducts[productIndex].copy(
                quantity = _state.value.addedProducts[productIndex].quantity - 1
            )

            val updatedList = _state.value.addedProducts.toMutableList().apply {
                this[productIndex] = updatedProduct
            }

            _state.update {
                it.copy(
                    addedProducts = updatedList
                )
            }
            // Buscamos el producto en la DB que sea igual al que estamos aucmento la cantidad
            viewModelScope.launch {
                productRepository.updateProduct(_state.value.addedProducts[productIndex])
            }
        }
        updateTotalTotals()
    }

    fun deleteProduct(productName: String) {
        val updatedList = _state.value.addedProducts.filter { it.name != productName }
        val product = _state.value.addedProducts.find { it.name == productName }
        _state.update {
            it.copy(
                addedProducts = updatedList
            )
        }
        viewModelScope.launch {
            if (product != null) productRepository.deleteProduct(product)
        }
        updateTotalTotals()
    }

    fun changeItemName(newName: String) {
        _state.update {
            it.copy(
                newItemName = newName
            )
        }
    }

    fun changePriceValue(newPrice: String) {
        _state.update {
            it.copy(
                newItemPrice = newPrice
            )
        }
    }

    // Método para mostrar el diálogo
    fun showDialogDelete() {
        _state.update {
            it.copy(openDialogDelete = true)
        }
    }

    // Método para ocultar el diálogo
    fun hideDialogDelete() {
        _state.update {
            it.copy(openDialogDelete = false)
        }
    }

    fun showDialogInsert() {
        _state.update {
            it.copy(openDialogInsert = true)
        }
    }

    // Método para ocultar el diálogo
    fun hideDialogInsert() {
        _state.update {
            it.copy(openDialogInsert = false)
        }
    }

    fun updateTotalTotals() {
        _state.update {
            it.copy(
                totalPrice = _state.value.addedProducts.sumOf { it.totalPrice },
                totalItems = _state.value.addedProducts.sumOf { it.quantity }
            )
        }
    }

    fun isEnabledAddButton() {
        if (_state.value.newItemName.isNotEmpty() && _state.value.newItemName.isNotBlank()
            && _state.value.newItemPrice.isNotEmpty() && _state.value.newItemPrice.isNotBlank()
        ) {
            _state.update {
                it.copy(
                    enableAddButton = true
                )
            }
        } else {
            _state.update {
                it.copy(
                    enableAddButton = false
                )
            }
        }
    }

    fun getProductList(): List<Product> {
        return _state.value.addedProducts
    }
}