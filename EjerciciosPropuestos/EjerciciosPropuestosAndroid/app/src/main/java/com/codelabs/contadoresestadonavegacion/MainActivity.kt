package com.codelabs.contadoresestadonavegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codelabs.contadoresestadonavegacion.ui.Navigation.AppNavigation
import com.codelabs.contadoresestadonavegacion.ui.theme.ContadoresEstadoNavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContadoresEstadoNavegacionTheme {
                AppNavigation()
            }
        }
    }
}

/**
 * OBJETIVO DE LA APP
 * 3 PANTALLAS
 *  INICIO
 *  CONTADORES
 *  RESULTADO CONTADORES
 *
 *  NAVEGACION ENTRE ELLAS PASANDO DE LA SEGUNA A LA TERCERA EL VALOR TOTAL(SUMA DE LOS CONTADORES)
 */
