package com.codelabs.contadorestado.ui.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class CounterScreenViewModel: ViewModel() {

    //No deberia usar un objeto contador y crear todos los que necesite, de modo que cree
    //tantos contadores como composable como contadores tenga definidos aqui ?

    //TODO
    /*
    Debo tener un modelo de data class para cada pantalla bajo un mismo viewmodel
    o varios viewmodel uno por cada pantalla cada uno con los dataclass que necesite?
     */
    private val _state = MutableStateFlow(CounterState(incrementoContador1 = 1))
    val state : StateFlow<CounterState> = _state.asStateFlow();

    fun cambiarIncremento (incremento: Int){
        _state.value = _state.value.copy(incrementoContador1 = incremento)
    }

    fun incrementarContador (){
        val nuevoAcumulado = _state.value.acumuladoContador1 + _state.value.incrementoContador1
        _state.value = _state.value.copy(acumuladoContador1 = nuevoAcumulado)
    }

    fun resetearContador(){
        _state.value = _state.value.copy(acumuladoContador1 = 0)
    }
    fun devolverTotal() : Int{
        return _state.value.acumuladoContador1
    }

}