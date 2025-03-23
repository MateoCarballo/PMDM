package com.codelabs.examenprimertrimestre.ui.theme.state

import androidx.lifecycle.ViewModel
import com.codelabs.examenprimertrimestre.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {
    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

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
        updateTotalPrice()
        updateTotalQuantity()
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
        }
        updateTotalPrice()
        updateTotalQuantity()
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
        }
        updateTotalPrice()
        updateTotalQuantity()
    }

    fun deleteProduct(productName: String) {
        val updatedList = _state.value.addedProducts.filter { it.name != productName }
        _state.update {
            it.copy(
                addedProducts = updatedList
            )
        }
        updateTotalPrice()
        updateTotalQuantity()
    }

    fun changeItemName(newName: String){
        _state.update{
            it.copy(
                newItemName = newName
            )
        }
    }

    fun changePriceValue(newPrice: String){
        _state.update{
            it.copy(
                newItemPrice = newPrice
            )
        }
    }

    fun updateTotalPrice(){
        _state.update {
            it.copy(
                totalPrice = _state.value.addedProducts.sumOf { it.totalPrice }
            )
        }
    }

    fun updateTotalQuantity(){
        _state.update {
            it.copy(
                totalItems = _state.value.addedProducts.sumOf { it.quantity }
            )
        }
    }

    fun isEnabledAddButton(){
        if (_state.value.newItemName.isNotEmpty() && _state.value.newItemName.isNotBlank()
            && _state.value.newItemPrice.isNotEmpty() && _state.value.newItemPrice.isNotBlank()){
            _state.update {
                it.copy(
                    enableAddButton = true
                )
            }
        }else{
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