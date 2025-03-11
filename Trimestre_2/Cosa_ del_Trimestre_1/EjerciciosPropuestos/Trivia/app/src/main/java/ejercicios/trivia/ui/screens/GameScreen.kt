package ejercicios.trivia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import ejercicios.trivia.data.Question
import ejercicios.trivia.data.Questions
import ejercicios.trivia.ui.state.gameScreen.GameScreenViewModel

@Composable
fun GameScreen(
    toResultScreen: (String, String) -> Unit,
    totalQuestions: String = "-1",
    gameScreenVM: GameScreenViewModel = viewModel(),
) {
    val state = gameScreenVM.state.collectAsState()

    // Solo se ejecutará la primera vez que se componga la pantalla
    LaunchedEffect(Unit) {
        gameScreenVM.setNumberOfQuestions(totalQuestions.toInt())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Pregunta ${state.value.usedQuestions.size + 1} / ${state.value.rounds}",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Question(
            question = state.value.question,
            selectedNumerIndex = state.value.selectedAnswer,
            isAnswered = state.value.answeredQuestion,
            amICorrect = { numberOption -> gameScreenVM.amICorrect(numberOption) },
            // TODO Dani porque asi no se ejecuta correctamente ?
            selectOption = gameScreenVM::selectOption
            //selectOption = { index -> trivialVM.selectOption(index) }
        )

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
                    if (state.value.selectedAnswer == state.value.question.correctAnswerIndex) {
                        gameScreenVM.addCorrectAnswer()
                    }
                } else {
                    // Solo avanza si la pregunta ya fue respondida
                    if (state.value.usedQuestions.size + 1 == state.value.rounds) {
                        toResultScreen(
                            state.value.correctAnswers.toString(),
                            state.value.rounds.toString()
                        )
                    } else {
                        gameScreenVM.updateQuesion()
                    }
                }
            }
        ) {
            Text(
                text = if (state.value.rounds > state.value.usedQuestions.size) {
                    if (state.value.answeredQuestion) "Continuar" else "Responder"
                } else {
                    "Mostrar Resultados"
                }
            )
        }
    }
}

@Composable
fun Question(
    question: Question = Questions.getRandomQuestion(),
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
                text = question.questionText,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            for ((index, option) in question.options.withIndex()) {
                Option(
                    numberOption = index,
                    optionText = option,
                    isAnswered = isAnswered,
                    amICorrect = amICorrect,
                    selectOption = { optionSelected -> selectOption(optionSelected) },
                    selectNumberIndex = selectedNumerIndex,
                )
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
        question = Question(
            correctAnswerIndex = 1,
            options = listOf("Opción 1", "Opción 2", "Opción 3"),
            questionText = "¿Cuál es la respuesta correcta?"
        ),
        selectedNumerIndex = -1,
        isAnswered = false,
        amICorrect = { it == 1 },
        selectOption = {}
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreen(
        toResultScreen = { _, _ -> }, // Se pasa la función correctamente
        totalQuestions = "5" // Se pasa un valor numérico válido
    )
}


