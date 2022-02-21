package chapter12

class BooleanQuestion(id: Int, text: String?) :
    Question(id, text!!, arrayOf("No", "Yes")) {
    override fun match(expected: Int, actual: Int): Boolean {
        return expected == actual
    }
}