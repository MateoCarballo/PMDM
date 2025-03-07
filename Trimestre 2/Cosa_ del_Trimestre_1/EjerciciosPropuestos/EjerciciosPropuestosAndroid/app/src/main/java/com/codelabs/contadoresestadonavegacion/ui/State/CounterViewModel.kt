package com.codelabs.contadoresestadonavegacion.ui.State

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.codelabs.contadoresestadonavegacion.Data.Contador

class CounterViewModel : ViewModel() {
    //Algun metodo que llamemos al movernos a la segunda
    // pantalla para crear todos los estados para cada contador creado


    //La lista es siempre la misma y por eso no recompone bien ni pasa bien parametros ??
    // No consigo parametrizar la creacion de contadores
    //private val _listadoContadores = mutableStateListOf(Contador())
    //val listadoContadores = _listadoContadores

    private val _listadoContadores = mutableStateListOf<Contador>()
    val listadoContadores: List<Contador> = _listadoContadores

    fun crearContadoresConParametro(numeroContadores : Int = 1){
        for (i in 1..numeroContadores) _listadoContadores.add(Contador())
    }

    fun modificarIncremento(index : Int = 0, increment : Int = 1): String {
        _listadoContadores[index].increment = increment
        return index.toString()
    }

    fun modificarTotal(index : Int = 0){
        _listadoContadores[index].total += listadoContadores[index].increment
    }

    fun sumaContadores():Int{
        var sumaContadores : Int = 0
        for (i in 0.._listadoContadores.size){
            sumaContadores=+ listadoContadores[i].total
        }
        return sumaContadores
    }

    fun contadoresCreadosEnLaLista():Int{
        return _listadoContadores.size
    }

}