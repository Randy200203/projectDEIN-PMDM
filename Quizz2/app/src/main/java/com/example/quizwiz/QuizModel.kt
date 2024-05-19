package com.example.quizwiz

/**
 * Data class representing a quiz in the QuizWiz app.
 *
 * This class holds information about a quiz, including its ID, title, subtitle, time limit, and a list of questions.
 *
 * @property id The unique identifier of the quiz.
 * @property title The title of the quiz.
 * @property subtitle The subtitle or description of the quiz.
 * @property time The time limit for completing the quiz.
 * @property questionList The list of questions included in the quiz.
 * @constructor Creates an instance of QuizModel.
 */
data class QuizModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String,
    val questionList: List<QuestionModel>
) {
    /**
     * Secondary constructor for creating an empty QuizModel.
     */
    constructor() : this("", "", "", "", emptyList())
}

/**
 * Data class representing a question in the QuizWiz app.
 *
 * This class holds information about a single question, including the question text, options, and the correct answer.
 *
 * @property question The text of the question.
 * @property options The list of options for the question.
 * @property correct The correct answer to the question.
 * @constructor Creates an instance of QuestionModel.
 */
data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correct: String
) {
    /**
     * Secondary constructor for creating an empty QuestionModel.
     */
    constructor() : this("", emptyList(), "")
}
