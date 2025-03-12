package ejercicios.trivia.network


import ejercicios.trivia.model.QuestionApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApiService{
    @GET("api.php")
    suspend fun getApiQuestions(
        @Query("amount") amount: Int = 10,
        @Query("type") type: String = "multiple"
    ): Response<QuestionApiResponse>
}