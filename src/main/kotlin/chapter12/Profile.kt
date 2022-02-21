package chapter12

class Profile {
    private var answer: Answer? = null

    fun matches(criterion: Criterion): Boolean {
        return answer != null && answer!!.match(criterion.answer)
    }

    fun add(answer: Answer?) {
        this.answer = answer

    }

}
