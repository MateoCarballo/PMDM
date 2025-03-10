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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ejercicios.trivia.data.Question
import ejercicios.trivia.data.Questions
import ejercicios.trivia.ui.state.gameScreen.GameScreenViewModel

@Composable
fun GameScreen(
    toResultScreen: () -> Unit
) {
    val trivialVM: GameScreenViewModel = remember { GameScreenViewModel() }
    val state = trivialVM.state.collectAsState()

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
            amICorrect = {numberOption -> trivialVM.amICorrect(numberOption)},
            // TODO Dani porque asi no se ejecuta correctamente ?
            selectOption = trivialVM::selectOption
            //selectOption = { index -> trivialVM.selectOption(index) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boton para confirmar la respuesta
        Button(
            enabled = state.value.selectedAnswer != -1,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                trivialVM.changeAnsweredQuestionValue()
                if (state.value.answeredQuestion) {
                    trivialVM.updateQuesion()
                    if (state.value.correctAnswers == state.value.selectedAnswer)trivialVM.addCorrectAnswer()
                }

                if (state.value.usedQuestions.size == state.value.rounds) {
                    // TODO navegar hacia pantalla final
                }
            }
        ) {
            Text(
                text = if(state.value.rounds > state.value.usedQuestions.size) {
                    if (state.value.answeredQuestion) "Continuar" else "Responder"
                }else{
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
    Question(correctAnswerIndex = 1, options = listOf("op1", "op2"), questionText = "question text")
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreen({})
}


