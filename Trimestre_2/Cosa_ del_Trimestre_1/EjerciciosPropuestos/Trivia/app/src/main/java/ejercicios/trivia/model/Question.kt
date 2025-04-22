package ejercicios.trivia.model

data class Question(
    val rawquestionText: String,
    val rawoptions: List<String>,
    val correctAnswerIndex: Int
) {
    val questionText: String
        get() = rawquestionText.decodeHtml()
    val options: List<String>
        get() = rawoptions.map {it.decodeHtml()}
}

private fun String.decodeHtml(): String {
    return android.text.Html.fromHtml(this, android.text.Html.FROM_HTML_MODE_LEGACY).toString()
}
