package chapter09

abstract class Question(private val id: Int, val text: String, private val answerChoices: Array<String>) {
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

class BooleanQuestion(id: Int, text: String?) :
    Question(id, text!!, arrayOf("No", "Yes")) {
    override fun match(expected: Int, actual: Int): Boolean {
        return expected == actual
    }
}