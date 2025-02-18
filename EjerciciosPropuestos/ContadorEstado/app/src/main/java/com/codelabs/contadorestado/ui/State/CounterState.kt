package com.codelabs.contadorestado.ui.State

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class CounterState {
    private val _incrementoContador1    : Int = 1
    private val _incrementoContador2    : Int = 1
    private val _acumuladoContador1     : Int = 1
    private val _acumuladoContador2     : Int = 1
}