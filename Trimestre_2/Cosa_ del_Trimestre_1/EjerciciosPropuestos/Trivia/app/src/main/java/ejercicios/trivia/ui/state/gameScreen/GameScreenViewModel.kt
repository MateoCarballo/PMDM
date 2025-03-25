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

class GameScreenViewModel(private val questionRepository: QuestionRepository) : ViewModel() {

    // Este es el estado del ViewModel
    private val _state = MutableStateFlow(
        GameScreenState(
            indexOfQuestion = 0,  // Inicializamos con 0, indicando la primera pregunta
            questionsFromApi = emptyList(),  // Lista vacía al principio
            question = null,  // Pregunta inicializada a null
            rounds = 5,  // Número de rondas
            selectedAnswer = -1,  // Sin respuesta seleccionada
            correctAnswers = 0,  // Correctas inicializadas a 0
            answeredQuestion = false,  // Ninguna pregunta ha sido respondida
            numberOfQuestionAnswered = 0,  // Ninguna pregunta respondida
            gameOver = false  // El juego no ha terminado
        )
    )

    val state: StateFlow<GameScreenState> = _state.asStateFlow()

    // Este método obtiene las preguntas de la API
    fun getAllQuestions(numberOfQuestions: Int) {
        viewModelScope.launch {
            try {
                // Llamada al repositorio para obtener las preguntas
                val apiQuestions = questionRepository.getQuestions(numberOfQuestions)

                // Convertimos las preguntas de la API al formato interno
                val convertedQuestions = apiQuestions.map { it.toQuestion() }

                // Actualizamos el estado con las preguntas obtenidas
                _state.update {
                    it.copy(
                        questionsFromApi = convertedQuestions,
                        question = convertedQuestions.firstOrNull()  // Selecciona la primera pregunta si existe
                    )
                }
            } catch (e: Exception) {
                // Manejo de errores
                println("Error al obtener preguntas: ${e.message}")
            }
        }
    }

    // Método para actualizar la pregunta actual
    fun updateQuestion() {
        // Verifica si hay más preguntas para mostrar
        if (_state.value.indexOfQuestion < _state.value.questionsFromApi.size - 1) {
            val newQuestionIndex = _state.value.indexOfQuestion + 1
            _state.update {
                it.copy(
                    indexOfQuestion = newQuestionIndex,
                    question = it.questionsFromApi[newQuestionIndex]
                )
            }
        }
    }

    // Método para añadir una respuesta correcta
    fun addCorrectAnswer() {
        _state.update {
            it.copy(correctAnswers = it.correctAnswers + 1)
        }
    }

    // Método para cambiar si una pregunta fue respondida
    fun changeAnsweredQuestionValue() {
        _state.update {
            it.copy(answeredQuestion = !it.answeredQuestion)
        }
    }

    // Método para seleccionar una opción de respuesta
    fun selectOption(newIndexSelected: Int) {
        _state.update {
            it.copy(
                selectedAnswer = newIndexSelected
            )
        }
    }

    // Método para verificar si la respuesta seleccionada es correcta
    fun amICorrect(selectedOptionIndex: Int): Boolean {
        return selectedOptionIndex == _state.value.question?.correctAnswerIndex
    }

    // Método para configurar el número de rondas
    fun setNumberOfQuestions(roundsNumber: Int) {
        _state.update {
            it.copy(
                rounds = roundsNumber
            )
        }
    }
}

