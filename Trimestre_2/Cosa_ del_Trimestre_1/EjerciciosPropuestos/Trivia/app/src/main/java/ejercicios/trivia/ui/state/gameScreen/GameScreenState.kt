package ejercicios.trivia.ui.state.gameScreen


import ejercicios.trivia.data.Questions
import ejercicios.trivia.model.Question
import ejercicios.trivia.model.QuestionApi

data class GameScreenState(
    val uiState: GameUiState = GameUiState.Loading,
    val indexOfQuestion: Int=0,
    val questionsFromApi: List<Question>,
    val question: Question? = null,
    val rounds: Int = 5,
    val selectedAnswer: Int = -1, // Si le das a responder sin seleccionar seleccionaria la primera
    val correctAnswers: Int = 0,
    val answeredQuestion: Boolean = false,
    val numberOfQuestionAnswered: Int = 0,
    val gameOver: Boolean = false,
    )

sealed interface GameUiState {
    data object Loading: GameUiState
    data object Success: GameUiState
    data object Error: GameUiState
}