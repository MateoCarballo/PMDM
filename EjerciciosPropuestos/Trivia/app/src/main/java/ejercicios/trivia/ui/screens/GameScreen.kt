package ejercicios.trivia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ejercicios.trivia.data.Question
import ejercicios.trivia.data.Questions
import ejercicios.trivia.ui.state.TrivialViewModel

@Composable
fun GameScreen() {
    val trivialVM = TrivialViewModel()
    val state = trivialVM.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            //Numero de pregunta
            text = "Pregunta numero ${state.value.usedQuestions.size + 1}",
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Question(
            question = trivialVM.getQuestion(),
            isAnswered = { trivialVM.isAnswered() },
            isCorrect = { index -> trivialVM.isCorrect(index) },
            changeAnserStatus = { trivialVM.changeAnswerValue() },
        )
    }
}

@Composable
fun Question(
    question: Question = Questions.getRandomQuestion(),
    isAnswered: () -> Boolean,
    isCorrect: (Int) -> Boolean,
    changeAnserStatus: () -> Unit,
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
                    option = option,
                    isAnswered = isAnswered,
                    isCorrect = isCorrect,
                    changeAnserStatus = changeAnserStatus
                )
            }
        }

    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {}
    ) {
        Text(
            text = "Responder"
        )
    }
}

@Composable
fun Option(
    numberOption: Int,
    option: String,
    isAnswered: () -> Boolean,
    isCorrect: (Int) -> Boolean,
    changeAnserStatus: () -> Unit,
) {
    Button(
        onClick = changeAnserStatus,
        modifier = if (isAnswered() && isCorrect(numberOption)) Modifier.background(Color.Green) else Modifier.background(
            Color.Red
        )
    ) {
        Text(
            text = option
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewQuestion() {
    Question(correctAnswerIndex = 1, options = listOf("op1","op2"), questionText = "question text")
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewGameScreen() {
    GameScreen()
}


