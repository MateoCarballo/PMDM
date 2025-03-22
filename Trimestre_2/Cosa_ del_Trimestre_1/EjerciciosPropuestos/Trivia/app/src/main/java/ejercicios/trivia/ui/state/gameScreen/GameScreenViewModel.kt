package ejercicios.trivia.ui.state.gameScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ejercicios.trivia.data.QuestionRepository
import ejercicios.trivia.data.Questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
TODO Dani retrofit
Aqui entiendo que tengo que meterle el question repository.
 Pero no necesitaria pasarle solo las preguntas desde la primera pantalla como parametro?
 */
class GameScreenViewModel(questionRepository: QuestionRepository) : ViewModel() {

    private val _state = MutableStateFlow(GameScreenState(
        questionsFromApi = TODO(),
        question = TODO(),
        rounds = TODO(),
        selectedAnswer = TODO(),
        correctAnswers = TODO(),
        answeredQuestion = TODO(),
        usedQuestions = TODO(),
        gameOver = TODO()
    ))
    val state: StateFlow<GameScreenState> = _state.asStateFlow()

    fun getAllQuestions() {
        viewModelScope.launch {
            try {
                // Llamada al repositorio
                val apiQuestions = questionRepository.getQuestions(10)

                // Convertimos las preguntas de la API a nuestro modelo interno
                val convertedQuestions = apiQuestions.map { it.toQuestion() }

                // Actualizamos el estado con las preguntas obtenidas
                _state.update { currentState ->
                    currentState.copy(
                        questionsFromApi = convertedQuestions,
                        question = convertedQuestions.first(),
                        usedQuestions = listOf(0)
                    )
                }
            } catch (e: Exception) {
                // Manejo básico de errores
                println("Error al obtener preguntas: ${e.message}")
            }
        }
    }
    
    fun updateQuesion() {
        //TODO conseguir una pregunta de la API. Antes aquí preguntaba a mi clase con preguntas Questions

    }

    fun addCorrectAnswer(){
        _state.value = _state.value.copy(correctAnswers = _state.value.correctAnswers + 1)
    }

    fun changeAnsweredQuestionValue(){
        _state.value = _state.value.copy(answeredQuestion = !_state.value.answeredQuestion)
    }

    fun selectOption(newIndexSelected: Int) {
        //_state.value = _state.value.copy(selectedAnswer = index)
        _state.update {
            currentState ->
            currentState.copy(
                selectedAnswer = newIndexSelected,
            )
        }
    }

    fun amICorrect(numerOption: Int) : Boolean{
        return numerOption == _state.value.question.correctAnswerIndex
    }

    fun setNumberOfQuestions(roundsNumber: Int){
        _state.update {
            curretState ->
            curretState.copy(
                rounds = roundsNumber
            )
        }
    }
}
