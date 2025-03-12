package ejercicios.trivia.data

import ejercicios.trivia.model.QuestionApi
import ejercicios.trivia.network.TriviaApiService

interface QuestionRepository{
    suspend fun getQuestions(quantity: Int): List<QuestionApi>
}

class NetWorkQuestionRepository(
    private val triviaApiService: TriviaApiService
) : QuestionRepository{
    override suspend fun getQuestions(quantity: Int): List<QuestionApi> {
        val response = triviaApiService.getApiQuestions(
            amount = quantity
        )
        return if(response.isSuccessful){
            response.body()?.results ?: emptyList()
        }else emptyList()
    }

}