package com.codelabs.examenprimertrimestre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.codelabs.examenprimertrimestre.ui.theme.ExamenPrimerTrimestreTheme
import com.codelabs.examenprimertrimestre.ui.theme.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenPrimerTrimestreTheme {
                AppNavigation()
            }
        }
    }
}

