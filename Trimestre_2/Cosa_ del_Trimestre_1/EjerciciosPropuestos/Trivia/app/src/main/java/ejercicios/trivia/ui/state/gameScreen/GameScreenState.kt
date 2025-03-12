package ejercicios.trivia.ui.state.gameScreen


import ejercicios.trivia.data.Questions
import ejercicios.trivia.model.Question

data class GameScreenState(
    val question: Question = Questions.getRandomQuestion(),
    val rounds: Int = 5,
    val selectedAnswer: Int = -1, // Si le das a responder sin seleccionar seleccionaria la primera
    val correctAnswers: Int = 0,
    val answeredQuestion: Boolean = false,
    val usedQuestions: List<Int> = listOf(),
    val gameOver: Boolean = false,
    )