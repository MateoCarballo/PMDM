package ejercicios.trivia.ui.state.gameScreen


import ejercicios.trivia.data.Questions
import ejercicios.trivia.model.Question
import ejercicios.trivia.model.QuestionApi

data class GameScreenState(
    val questionsFromApi: List<QuestionApi> = listOf(), //TODO para que no falle le pongo la lista vacia pendiente de como llenar de preguntas la lista
    val question: Question = Questions.getRandomQuestion(),
    val rounds: Int = 5,
    val selectedAnswer: Int = -1, // Si le das a responder sin seleccionar seleccionaria la primera
    val correctAnswers: Int = 0,
    val answeredQuestion: Boolean = false,
    val usedQuestions: List<Int> = listOf(),
    val gameOver: Boolean = false,
    )