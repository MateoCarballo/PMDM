package ejercicios.trivia.ui.state.gameScreen

import android.util.Log
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
        GameScreenState()
    )

    val state: StateFlow<GameScreenState> = _state.asStateFlow()

    init {
        getAllQuestions()
    }
    // Este obtiene las preguntas de la API
    fun getAllQuestions(numberOfQuestions: Int) {
        viewModelScope.launch {
            try {
                Log.d("MiTag", "Entrando al método miMetodo()")
                // Llamada al repositorio para obtener las preguntas
                val apiQuestions = questionRepository.getQuestions(numberOfQuestions)
                Log.d("MiTag", "Despues de la llamada al repo para obtener preguntas")                // Convertimos las preguntas de la API al formato interno
                //val convertedQuestions = apiQuestions.forEach() { it.toQuestion() }
                +val convertedQuestions = apiQuestions.forEach() { it.toQuestion() }
                Log.d("MiTag", "Despues de transformar el formato al de mis Question")
                _state.update {
                    it.copy(
                        questionsFromApi = convertedQuestions,
                        question = convertedQuestions.getOrNull(0)  // Selecciona la primera pregunta si existe
                    )
                }
                Log.d("MiTag", "Saliendo al método miMetodo()")
            } catch (e: Exception) {
                // Manejo de errores
                println("Error al obtener preguntas: ${e.message}")
            }

        }
        // Actualizamos el estado con las preguntas obtenidas

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

