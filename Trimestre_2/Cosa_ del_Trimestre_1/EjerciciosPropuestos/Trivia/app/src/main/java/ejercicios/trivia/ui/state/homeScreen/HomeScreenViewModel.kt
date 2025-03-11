package ejercicios.trivia.ui.state.homeScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.round

class HomeScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    fun incrementRounds(){
        _state.update {
            currentState ->
            currentState.copy(
                roundsSelected = state.value.roundsSelected + 1,
                )
        }
    }

    fun decrementRounds(){
        _state.update {
                currentState ->
            currentState.copy(
                roundsSelected = state.value.roundsSelected - 1,
            )
        }
    }
}