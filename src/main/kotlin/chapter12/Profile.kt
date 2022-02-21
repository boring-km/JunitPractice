package chapter12

class Profile {
    private var answers: MutableMap<String, Answer> = HashMap()

    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun match(criteria: Criteria): ProfileMatch {
        return ProfileMatch(answers, criteria)
    }
}
