package ejercicios.trivia.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionApi(
    @SerialName("question")
    val question: String,
    @SerialName("correct_answer")
    val correct_answer: String,
    @SerialName("incorrect_answer")
    val incorrect_answer: List<String>
) {
    fun toQuestion(): Question {
        val options = incorrect_answer.toMutableList()
        options.add(correct_answer)
        val correctIndex = options.indexOf(correct_answer)
        return Question(question,options.shuffled(),correctIndex)
    }
}
