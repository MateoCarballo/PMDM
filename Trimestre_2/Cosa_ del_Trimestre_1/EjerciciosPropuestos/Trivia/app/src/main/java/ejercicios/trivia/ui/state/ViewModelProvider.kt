package ejercicios.trivia.ui.state

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ejercicios.trivia.TriviaApplication
import ejercicios.trivia.ui.state.gameScreen.GameScreenViewModel

/*
TODO DANI retrofit
No se como hacer para crear una vm diferente dependiendo de la pantalla
 */

class ViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as TriviaApplication)
            val questionRepository = application.container.questionRepository
            GameScreenViewModel(questionRepository)
        }
    }


}