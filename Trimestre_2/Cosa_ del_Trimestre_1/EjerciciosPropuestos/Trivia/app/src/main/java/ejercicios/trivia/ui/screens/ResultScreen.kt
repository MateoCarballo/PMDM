package ejercicios.trivia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(
    toHomeScreen: () -> Unit,
    correctAnswers: String,
    numberOfQuestions: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Tu puntuacion ha sido",
            fontSize = 20.sp,
            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        )
        Spacer(Modifier.padding(16.dp))
        Text(
            text = "$correctAnswers/$numberOfQuestions",
            fontSize = 40.sp,
            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        )
        Spacer(Modifier.padding(64.dp))

        Button(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            onClick = toHomeScreen
        ) {
            Text("Volver a jugar ")
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Boton para volver a jugar"
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen(
        toHomeScreen = {},
        correctAnswers = "7",
        numberOfQuestions = "10")
}