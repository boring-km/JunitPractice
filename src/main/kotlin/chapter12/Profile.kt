package chapter12

class Profile {
    private var answers: MutableMap<String, Answer> = HashMap()

    private fun getMatchingProfileAnswer(criterion: Criterion): Answer? {
        return answers[criterion.answer.questionText]
    }

    fun matches(criterion: Criterion): Boolean {
        val answer = getMatchingProfileAnswer(criterion)
        return answer != null && answer.match(criterion.answer)
    }

    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun matches(criterion: Criteria): Boolean {
        return false
    }

}
