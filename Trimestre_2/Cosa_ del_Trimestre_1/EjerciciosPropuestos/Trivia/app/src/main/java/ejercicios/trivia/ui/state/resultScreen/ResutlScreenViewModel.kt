package ejercicios.trivia.ui.state.resultScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ResutlScreenViewModel : ViewModel(){

    private val _state = MutableStateFlow(ResutlScreenState())
    val state: StateFlow<ResutlScreenState> = _state.asStateFlow()

    fun updateCorrectAnswers(score: Int){
        _state.update {
            currentState ->
            currentState.copy(
                correctAnswers = score,
            )
        }
    }
}