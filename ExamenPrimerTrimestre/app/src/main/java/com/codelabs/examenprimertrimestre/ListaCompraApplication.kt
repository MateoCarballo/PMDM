package com.codelabs.examenprimertrimestre

import android.app.Application
import com.codelabs.examenprimertrimestre.data.AppContainer

class ListaCompraApplication : Application() {
    lateinit var appContainer : AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}