package chapter09

class MatchSet(private var answers: AnswerCollection, private var criteria: Criteria) {

    init {
        calculateScore(criteria)
    }

    private fun calculateScore(criteria: Criteria): Int {
        var score = 0
        for (criterion: Criterion in criteria) {
            if (criterion.matches(answers.answerMatching(criterion)))
                score += criterion.weight.value
        }
        return score
    }

    fun matches(): Boolean {
        if (doesNotMeetAnyMustMatchCriterion(criteria)) // 특정 조건에 걸리면 false
            return false
        return anyMatches(criteria) // 조건이 맞는 다른 경우를 찾기
    }

    private fun doesNotMeetAnyMustMatchCriterion(criteria: Criteria): Boolean {

        for (criterion: Criterion in criteria) {
            val match = criterion.matches(answers.answerMatching(criterion))
            if (!match && criterion.weight == Weight.MustMatch) {
                return true
            }
        }
        return false
    }

    private fun anyMatches(criteria: Criteria) : Boolean {
        var anyMatches = false
        for (criterion: Criterion in criteria) {
            anyMatches = anyMatches or criterion.matches(answers.answerMatching(criterion))
        }
        return anyMatches
    }
}
