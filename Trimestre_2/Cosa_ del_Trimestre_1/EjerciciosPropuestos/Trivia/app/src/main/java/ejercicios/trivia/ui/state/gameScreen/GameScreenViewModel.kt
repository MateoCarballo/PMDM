package ejercicios.trivia.ui.state.gameScreen

import androidx.lifecycle.ViewModel
import ejercicios.trivia.data.QuestionRepository
import ejercicios.trivia.data.Questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
/*
TODO Dani retrofit
Aqui entiendo que tengo que meterle el question repository.
Pero no necesitaria pasarle solo las preguntas desde la primera pantalla como parametro?
 */
class GameScreenViewModel(questionRepository: QuestionRepository) : ViewModel() {

    private val _state = MutableStateFlow(GameScreenState())
    val state: StateFlow<GameScreenState> = _state.asStateFlow()

    fun updateQuesion() {
        var questionNumber = (0..19).random()
        do{
            if (_state.value.usedQuestions.contains(questionNumber)) questionNumber = (0..19).random()
        }while (_state.value.usedQuestions.contains(questionNumber))
        //TODO Preguntar a Dani porque este metodo y con el copy de antes no me iba
        _state.update { currentState ->
            currentState.copy(
                question = Questions.getOneQuestion(questionNumber),
                usedQuestions = currentState.usedQuestions + questionNumber,
                selectedAnswer = -1, // Resetea la selección
                answeredQuestion = false // Reinicia el estado de respuesta
            )
        }
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
