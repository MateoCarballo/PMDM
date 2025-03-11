package ejercicios.trivia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ejercicios.trivia.ui.state.homeScreen.HomeScreenViewModel

@Composable
fun HomeScreen(
    toGameScreen: (String) -> Unit,
) {
    val homeScreenVM: HomeScreenViewModel = remember { HomeScreenViewModel() }
    val state = homeScreenVM.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Bienvenido a Trivia App",
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Row(
            //modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.LightGray),
                onClick = {
                    homeScreenVM.decrementRounds()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Boton añadir",
                )
            }
            Spacer(Modifier.padding(8.dp))
            Text("Numero de preguntas")
            Spacer(Modifier.padding(4.dp))
            Text(state.value.roundsSelected.toString())
            Spacer(Modifier.padding(8.dp))
            IconButton(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.LightGray),
                onClick = {
                    homeScreenVM.incrementRounds()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Boton añadir",
                )
            }
        }
        Spacer(Modifier.padding(16.dp))
        Button(
            onClick = {
                toGameScreen(state.value.roundsSelected.toString())
            }
        ) {
            Text("Play")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(toGameScreen = {})
}