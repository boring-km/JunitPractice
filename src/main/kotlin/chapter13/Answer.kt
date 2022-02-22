package chapter13

class Answer {
    private var i: Int
    var question: Question
        private set

    constructor(question: Question, i: Int) {
        this.question = question
        this.i = i
    }

    constructor(question: Question, matchingValue: String) {
        this.question = question
        i = question.indexOf(matchingValue)
    }

    companion object {
        fun nullObject(): Answer {
            return Answer(NullQuestion(0, "", arrayOf("")), 0)
        }
    }

    val questionText: String
        get() = question.text

    override fun toString(): String {
        return String.format("%s %s", question.text, question.getAnswerChoice(i))
    }

    fun match(expected: Int): Boolean {
        return question.match(expected, i)
    }

    fun match(otherAnswer: Answer): Boolean {
        return question.match(i, otherAnswer.i)
    }
}