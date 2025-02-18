package com.codelabs.contadorestado.ui.State

import androidx.lifecycle.ViewModel
import androidx.navigation.NamedNavArgument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ContadorViewModel: ViewModel(), List<NamedNavArgument> {

    //No deberia usar un objeto contador y crear todos los que necesite, de modo que cree
    //tantos contadores como composable como contadores tenga definidos aqui ?

    /*
    private val _incrementoContador1 = MutableStateFlow(1)
    val incrementoContador1 : StateFlow<Int> = _incrementoContador1

    private val _incrementoContador2 = MutableStateFlow(1)
    val incrementoContador2 : StateFlow<Int> = _incrementoContador2

    private val _acumuladoContador1 = MutableStateFlow(0)
    val acumuladoContador1 : StateFlow<Int> = _acumuladoContador1

    private val _acumuladoContador2 = MutableStateFlow(0)
    val acumuladoContador2 : StateFlow<Int> = _acumuladoContador1
     */

    //TODO lo rompi guacho
    private val _state :CounterState = rememberState

    fun cambiarIncremento1 (incremento: Int = 1){
        _incrementoContador1.update {it}
    }

    fun cambiarIncremento2 (incremento: Int = 1){
        _incrementoContador2.update {it}
    }

    fun incrementarContador1 (){
        _acumuladoContador1.update { it + incrementoContador1.value }
    }

    fun incrementarContador2 (){
        _acumuladoContador2.update { it + incrementoContador2.value }
    }

    fun obtenerAcumulado(): Int {
        return acumuladoContador1.value + acumuladoContador2.value
    }

    fun resetearContadores(){
        _acumuladoContador1.value = 0;
        _acumuladoContador2.value = 0;
    }

    override val size: Int
        get() = TODO("Not yet implemented")

    override fun get(index: Int): NamedNavArgument {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<NamedNavArgument> {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<NamedNavArgument> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<NamedNavArgument> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<NamedNavArgument> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: NamedNavArgument): Int {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: NamedNavArgument): Int {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<NamedNavArgument>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: NamedNavArgument): Boolean {
        TODO("Not yet implemented")
    }

}