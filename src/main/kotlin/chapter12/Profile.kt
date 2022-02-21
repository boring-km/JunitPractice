package chapter12

class Profile {
    private var answer: Answer? = null

    fun matches(criterion: Criterion): Boolean {
        return answer != null
    }

    fun add(answer: Answer?) {
        this.answer = answer

    }

}
