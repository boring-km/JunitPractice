package chapter12

abstract class Question(val id: Int, val text: String, private val answerChoices: Array<String>) {

    fun getAnswerChoice(i: Int): String {
        return answerChoices[i]
    }

    fun match(answer: Answer?): Boolean {
        return false
    }

    abstract fun match(expected: Int, actual: Int): Boolean
    fun indexOf(matchingAnswerChoice: String): Int {
        for (i in answerChoices.indices) if (answerChoices[i] == matchingAnswerChoice) return i
        return -1
    }
}