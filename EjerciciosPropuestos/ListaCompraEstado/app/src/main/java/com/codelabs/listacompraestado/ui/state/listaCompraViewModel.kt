package com.codelabs.listacompraestado.ui.state

import androidx.lifecycle.ViewModel
import com.codelabs.listacompraestado.ui.data.ItemCompra
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class listaCompraViewModel : ViewModel(){
    private val _state = MutableStateFlow(StateListaCompra())
    val state : StateFlow<StateListaCompra> = _state.asStateFlow()


    /* TODO preguntar Dani 2
    fun añadirItem(newList: List<ItemCompra>){
         Mi idea es crear una nueva lista en la pantalla y pasarsela directamente aqui
         y copiar la lista creada con todos los items mas el que entra en la nueva
    }
     */
    /* TODO preguntar Dani 3

    No me deja hacer el copy

    fun añadirItem(newItem : ItemCompra){
        _state.value.lista.add(ItemCompra())
        _state.value = _state.value.copy(lista = _state.value.lista)
    }
     */

    fun añadirItem(newItem : ItemCompra){
        //Copio la lista actual
        val listaCopiada : List<ItemCompra> = _state.value.lista + newItem
        //Copiar la nueva lista en la lista del estado
        _state.value = _state.value.copy(lista = listaCopiada)
    }

    fun eliminarElemento(){

    }
}
