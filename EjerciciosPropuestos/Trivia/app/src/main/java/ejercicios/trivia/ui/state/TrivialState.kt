package ejercicios.trivia.ui.state

import ejercicios.trivia.data.Question
import ejercicios.trivia.data.Questions

data class TrivialState(
    val question: Question = Questions.getRandomQuestion(),
    val rounds: Int = 5,
    val correctAnswers: Int = 0,
    val usedQuestions: List<Int> = listOf(),
    val gameOver: Boolean = false,
    val answeredQuestion: Boolean = false,
)