package ejercicios.trivia

import android.app.Application
import ejercicios.trivia.data.AppContainer
import ejercicios.trivia.data.DefaultAppContainer

class TriviaApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}