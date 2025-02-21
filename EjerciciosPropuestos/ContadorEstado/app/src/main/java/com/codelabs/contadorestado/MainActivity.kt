package com.codelabs.contadorestado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codelabs.contadorestado.ui.navigation.AppNavigation
import com.codelabs.contadorestado.ui.theme.ContadorEstadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavigationPreview() {
    ContadorEstadoTheme {
        AppNavigation()
    }
}
/*
Preguntas
1 - Necesito pasar el navcontroller junto con el modelview o puedo meter la nevagacion dentro del modelview?
2 - Ubicacion correcta de la clase usada para definir las rutas de las screen?
3 - Porque usar mutable state of y MutableStateFlow?
No entiendo porque elevas los contadores al view model pero cambias el mutable de mutable state of a mutableflow
4 - Cuando pasas una ruta para navegar que concatenas con el simbolo '/' porque lo haces
5 - Estructura de carpetas
 */