package com.codelabs.examenprimertrimestre.ui.theme.state

import androidx.lifecycle.ViewModel
import com.codelabs.examenprimertrimestre.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {
    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

    fun addNewProduct(newItemName: String, newItemPrice: String) {
        if (newItemName.isNotBlank() && newItemName.isNotEmpty() && newItemPrice.isNotBlank() && newItemPrice.isNotEmpty() ){
            val price = newItemPrice.toDoubleOrNull()
            if (price != null && price > 0){
                val newProduct = Product(name=newItemName,price = price)

                _state.update {
                    it.copy(
                        addedProducts = it.addedProducts + newProduct
                    )
                }
            }
        }
    }

    fun increaseProduct(productName: String){
        val productIndex = state.value.addedProducts.indexOfFirst { it.name == productName }

        if (state.value.addedProducts[productIndex].quantity <= 99){
            val updatedProduct = state.value.addedProducts[productIndex].copy(
                quantity = state.value.addedProducts[productIndex].quantity + 1
            )

            val updatedList = state.value.addedProducts.toMutableList().apply {
                this[productIndex] = updatedProduct
            }

            _state.update{
                it.copy(
                    addedProducts = updatedList
                )
            }
        }
        
    }

    fun decreaseProduct(productName: String){
        val productIndex = state.value.addedProducts.indexOfFirst { it.name == productName }

        if (state.value.addedProducts[productIndex].quantity >= 1){
            val updatedProduct = state.value.addedProducts[productIndex].copy(
                quantity = state.value.addedProducts[productIndex].quantity - 1
            )

            val updatedList = state.value.addedProducts.toMutableList().apply {
                this[productIndex] = updatedProduct
            }

            _state.update{
                it.copy(
                    addedProducts = updatedList
                )
            }
        }

    }

    fun getProductList(): List<Product>{
        return _state.value.addedProducts
    }
}