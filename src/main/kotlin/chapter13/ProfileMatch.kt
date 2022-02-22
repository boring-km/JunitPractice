package chapter13

@Suppress("DuplicatedCode")
class ProfileMatch(private val answers: MutableMap<String, Answer>, private val criteria: Criteria) {
    var score = 0
    var isMatch = false

    private fun matches(): Boolean {
        var matches = false
        for (criterion: Criterion in criteria) {
            if (matches(criterion)) {
                matches = true
            } else if (criterion.weight == Weight.MustMatch) {
                return false
            }
        }
        if (matches) score = 100
        return matches
    }

    init {
        isMatch = matches()
    }

    private fun matches(criterion: Criterion): Boolean {
        return criterion.weight == Weight.DontCare ||
                criterion.answer.match(getMatchingProfileAnswer(criterion))
    }

    @Suppress("DuplicatedCode")
    private fun getMatchingProfileAnswer(criterion: Criterion): Answer {
        return answers[criterion.answer.questionText].let { Answer.nullObject() }
    }
}
