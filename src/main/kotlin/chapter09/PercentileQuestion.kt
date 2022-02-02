package chapter09

class PercentileQuestion(id: Int, text: String?, answerChoices: Array<String>) :
    Question(id, text!!, answerChoices) {

    override fun match(expected: Int, actual: Int): Boolean {
        return expected <= actual
    }
}