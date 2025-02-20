package com.codelabs.contadorestado.ui.State

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CounterState(
    val incrementoContador1    : Int = 1,
    val acumuladoContador1     : Int = 1,
)