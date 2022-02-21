package chapter12

class Profile {
    private var answers: MutableMap<String, Answer> = HashMap()

    private fun getMatchingProfileAnswer(criterion: Criterion): Answer? {
        return answers[criterion.answer.questionText]
    }

    fun matches(criterion: Criterion): Boolean {
        return criterion.weight == Weight.DontCare ||
                criterion.answer.match(getMatchingProfileAnswer(criterion))
    }

    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun matches(criteria: Criteria): Boolean {
        var matches = false
        for (criterion: Criterion in criteria) {
            if (matches(criterion)) {
                matches = true
            } else if (criterion.weight == Weight.MustMatch) {
                return false
            }
        }
        return matches
    }

    fun match(criteria: Criteria): ProfileMatch {
        TODO("Not yet implemented")
    }

}
