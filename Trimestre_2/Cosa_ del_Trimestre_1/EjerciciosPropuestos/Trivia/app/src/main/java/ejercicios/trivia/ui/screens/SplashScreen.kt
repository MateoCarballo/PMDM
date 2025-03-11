package ejercicios.trivia.ui.screens

import android.text.Layout
import android.window.SplashScreen
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    toHomeScreen: () -> Unit,
) {
    SplashEffect(toHomeScreen = toHomeScreen)
}

@Composable
fun SplashEffect(
    toHomeScreen: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(5000L) // Espera 5 segundos
        toHomeScreen() // Navega a la siguiente pantalla
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(
            modifier = Modifier,
            text = "TRIVIA APP",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )
        Spacer(Modifier.padding(vertical = 16.dp))
        Text("Loading ... ")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen({})
}

