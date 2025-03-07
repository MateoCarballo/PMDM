package ejercicios.trivia.ui.state

import ejercicios.trivia.data.Question
import ejercicios.trivia.data.Questions

data class TrivialState(
    val question: Question = Questions.getRandomQuestion(),
    val rounds: Int = 5,
    val selectedAnswer: Int = -1, // Si le das a responder sin seleccionar seleccionaria la primera
    val correctAnswers: Int = 0,
    val answeredQuestion: Boolean = false,
    val usedQuestions: List<Int> = listOf(),
    val gameOver: Boolean = false,

    )