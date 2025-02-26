package ejercicios.trivia.ui.state

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import ejercicios.trivia.data.Question
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
        }while (!_state.value.usedQuestions.contains(questionNumber))
        val usedQuestionsUpdated = _state.value.usedQuestions + questionNumber
        _state.value = _state.value.copy(
            question = Questions.getOneQuestion(questionNumber),
            usedQuestions = usedQuestionsUpdated,
        )
    }

    fun addCorrectAnswer(){
        _state.value = _state.value.copy(correctAnswers =+1)
    }

    fun getQuestion(): Question{
        return state.value.question
    }

    fun isAnswered(): Boolean{
        return _state.value.answeredQuestion
    }
    fun isCorrect(numberOption: Int): Boolean{
        return numberOption == _state.value.question.correctAnswerIndex
    }
    fun changeAnswerValue(){
        _state.value = _state.value.copy(answeredQuestion = !_state.value.answeredQuestion)
    }
}
