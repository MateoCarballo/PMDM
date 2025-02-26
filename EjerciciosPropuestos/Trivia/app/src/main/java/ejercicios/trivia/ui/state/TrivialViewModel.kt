package ejercicios.trivia.ui.state

import androidx.lifecycle.ViewModel
import ejercicios.trivia.data.Questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrivialViewModel : ViewModel() {

    private val _state = MutableStateFlow(TrivialState())
    val state: StateFlow<TrivialState> = _state.asStateFlow()

    fun updateQuesion() {
        var questionNumber = (0..20).random()
        do{
            if (_state.value.usedQuestions.contains(questionNumber)) questionNumber = (0..20).random()
        }while (_state.value.usedQuestions.contains(questionNumber))
        _state.value = _state.value.copy(
            question = Questions.getOneQuestion(questionNumber),
            usedQuestions = state.value.usedQuestions + questionNumber,
        )
    }

    fun addCorrectAnswer(){
        _state.value = _state.value.copy(correctAnswers = _state.value.correctAnswers + 1)
    }

    fun isCorrect(): Boolean{
        return _state.value.selectedAnswer == _state.value.question.correctAnswerIndex
    }

    fun changeAnsweredQuestionValue(){
        _state.value = _state.value.copy(answeredQuestion = !_state.value.answeredQuestion)
    }

    fun selectOption(index: Int) {
        _state.value = _state.value.copy(selectedAnswer = index)
    }

    fun amICorrect(numerOption: Int) : Boolean{
        return numerOption == _state.value.question.correctAnswerIndex
    }
}
