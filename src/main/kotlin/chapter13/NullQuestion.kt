package chapter13

class NullQuestion(id: Int, text: String, answerChoices: Array<String>) : Question(id, text, answerChoices) {
    override fun match(expected: Int, actual: Int): Boolean {
        return false
    }
}