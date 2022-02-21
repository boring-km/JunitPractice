package chapter12

class Answer {
    private var i: Int
    private val question: Question

    constructor(question: Question, i: Int) {
        this.question = question
        this.i = i
    }

    constructor(question: Question, matchingValue: String) {
        this.question = question
        i = question.indexOf(matchingValue)
    }

    val questionText: String
        get() = question.text

    override fun toString(): String {
        return java.lang.String.format("%s %s", question.text, question.getAnswerChoice(i))
    }

    fun match(expected: Int): Boolean {
        return question.match(expected, i)
    }

    fun match(otherAnswer: Answer?): Boolean {
        return if (otherAnswer == null) false else question.match(i, otherAnswer.i)
    }
}