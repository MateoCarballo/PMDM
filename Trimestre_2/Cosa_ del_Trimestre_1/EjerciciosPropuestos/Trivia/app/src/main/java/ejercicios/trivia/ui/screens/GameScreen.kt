package ejercicios.trivia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ejercicios.trivia.model.Question
import ejercicios.trivia.ui.state.ViewModelProvider
import ejercicios.trivia.ui.state.gameScreen.GameScreenViewModel

@Composable
fun GameScreen(
    toResultScreen: (String, String) -> Unit,
    gameScreenVM: GameScreenViewModel = viewModel(factory = ViewModelProvider.Factory),
) {
    val state = gameScreenVM.state.collectAsState()

    LaunchedEffect(Unit) {
        gameScreenVM.getAllQuestions(10)
    }

    if (state.value.questionsFromApi.isEmpty()){
        LoadingScreen()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Pregunta ${state.value.numberOfQuestionAnswered + 1} / ${state.value.rounds}",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (state.value.question != null){
                Question(
                    question = state.value.question,
                    selectedNumerIndex = state.value.selectedAnswer,
                    isAnswered = state.value.answeredQuestion,
                    amICorrect = { numberOption -> gameScreenVM.amICorrect(numberOption) },
                    // TODO Dani porque asi no se ejecuta correctamente ?
                    selectOption = gameScreenVM::selectOption
                    //selectOption = { index -> trivialVM.selectOption(index) }
                )
            }else{
                Text("Error al cargar la pregunta")
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Boton para confirmar la respuesta
            Button(
                enabled = state.value.selectedAnswer != -1,
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    if (!state.value.answeredQuestion) {
                        // Primero marca la pregunta como respondida
                        gameScreenVM.changeAnsweredQuestionValue()

                        // Si la respuesta es correcta, sumar un punto
                        if (state.value.selectedAnswer == state.value.question?.correctAnswerIndex) {
                            gameScreenVM.addCorrectAnswer()
                        }
                    } else {
                        // Solo avanza si la pregunta ya fue respondida
                        if (state.value.numberOfQuestionAnswered + 1 == state.value.rounds) {
                            toResultScreen(
                                state.value.correctAnswers.toString(),
                                state.value.rounds.toString()
                            )
                        } else {
                            gameScreenVM.updateQuestion()
                        }
                    }
                }
            ) {
                Text(
                    text = if (state.value.rounds > state.value.numberOfQuestionAnswered) {
                        if (state.value.answeredQuestion) "Continuar" else "Responder"
                    } else {
                        "Mostrar Resultados"
                    }
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    // Contenedor centrado en la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator() // Indicador de carga
            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre el círculo y el texto
            Text(
                text = "Cargando preguntas...",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun Question(
    question: Question?,
    selectedNumerIndex: Int,
    isAnswered: Boolean,
    amICorrect: (Int) -> Boolean,
    selectOption: (Int) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
        modifier = Modifier.padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = question?.questionText ?: "No tengo pregunta",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            question?.options?.let {
                for ((index, option) in question.options.withIndex())
                {
                    Option(
                        numberOption = index,
                        optionText = option,
                        isAnswered = isAnswered,
                        amICorrect = amICorrect,
                        selectOption = { optionSelected -> selectOption(optionSelected) },
                        selectNumberIndex = selectedNumerIndex,
                    )
                }
            } ?: run {
                // Aquí puedes manejar el caso cuando question es null
                // Por ejemplo, puedes mostrar un mensaje o no hacer nada
                Text("No hay opciones disponibles.")
            }
        }

    }
}

@Composable
fun Option(
    selectNumberIndex: Int,
    numberOption: Int,
    optionText: String,
    isAnswered: Boolean,
    amICorrect: (Int) -> Boolean,
    selectOption: (Int) -> Unit
) {
    val backGroundColor = when {
        isAnswered && amICorrect(numberOption) -> Color.Green
        isAnswered && !amICorrect(numberOption) -> Color.Red
        selectNumberIndex == numberOption -> Color.LightGray
        else -> Color.Transparent
    }

    val textColor = when {
        backGroundColor == Color.Green || backGroundColor == Color.Red -> Color.White
        else -> Color.Black
    }

    Button(
        onClick = {
            selectOption(numberOption)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = backGroundColor
        )
    ) {
        Text(
            text = optionText,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuestion() {
    Question(
        rawquestionText = TODO(),
        rawoptions = TODO(),
        correctAnswerIndex = TODO()
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreen(
        toResultScreen = { _, _ -> }, // Se pasa la función correctamente
    )
}


