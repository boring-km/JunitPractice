package chapter09

class Answer {
    private var i: Int
    private var question: Question

    constructor(question: Question, i: Int) {
        this.question = question
        this.i = i
    }

    constructor(characteristic: Question, matchingValue: String) {
        question = characteristic
        i = characteristic.indexOf(matchingValue)
    }

    val questionText: String
        get() = question.text

    override fun toString(): String {
        return java.lang.String.format("%s %s", question.text, question.getAnswerChoice(i))
    }

    fun match(expected: Int): Boolean {
        return question.match(expected, i)
    }

    fun match(otherAnswer: Answer): Boolean {
        return question.match(i, otherAnswer.i)
    }

    val characteristic: Question
        get() = question
}