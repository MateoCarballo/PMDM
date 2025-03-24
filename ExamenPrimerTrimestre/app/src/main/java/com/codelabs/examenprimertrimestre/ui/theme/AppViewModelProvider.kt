package com.codelabs.examenprimertrimestre.ui.theme

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.codelabs.examenprimertrimestre.ListaCompraApplication
import com.codelabs.examenprimertrimestre.ui.theme.state.ListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ListViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                productRepository = listaCompraApp().appContainer.productRepository,
            )
        }
    }
}

fun CreationExtras.listaCompraApp(): ListaCompraApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ListaCompraApplication)