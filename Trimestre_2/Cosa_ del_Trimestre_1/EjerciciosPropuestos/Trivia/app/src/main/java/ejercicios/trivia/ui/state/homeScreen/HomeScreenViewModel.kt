package ejercicios.trivia.ui.state.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {

    private val TAG = "HS_VM"

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    fun incrementRounds(){
        Log.i(TAG, "he entrado en incrementRounds")
        _state.update {
            currentState ->
            currentState.copy(
                roundsSelected = if(state.value.roundsSelected < 20)state.value.roundsSelected + 1 else 20
                )
        }
    }

    fun decrementRounds(){
        _state.update {
                currentState ->
            currentState.copy(
                roundsSelected = if(state.value.roundsSelected > 1)state.value.roundsSelected - 1 else 1,
            )
        }
    }
}