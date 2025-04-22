package ejercicios.trivia.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionApiResponse(
    val response_code : Int,
    val results: List<QuestionApi>
)
