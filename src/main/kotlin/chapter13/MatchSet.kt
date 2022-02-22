package chapter13


open class MatchSet(val profileId: String, private val answers: Map<String, Answer>?, private val criteria: Criteria?) :
    Comparable<MatchSet> {
    private var score = Int.MIN_VALUE

    open fun getScore(): Int {
        if (score == Int.MIN_VALUE) calculateScore()
        return score
    }

    private fun calculateScore() {
        score = 0
        if (criteria != null) {
            for (criterion in criteria) if (criterion.matches(answerMatching(criterion)!!)) score += criterion.weight.value
        }
    }

    private fun answerMatching(criterion: Criterion): Answer? {
        return answers?.get(criterion.answer.questionText)
    }

    fun matches(): Boolean {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
        }
        return if (doesNotMeetAnyMustMatchCriterion()) false else anyMatches()
    }

    private fun doesNotMeetAnyMustMatchCriterion(): Boolean {
        if (criteria != null) {
            for (criterion in criteria) {
                val match = criterion.matches(answerMatching(criterion)!!)
                if (!match && criterion.weight === Weight.MustMatch) return true
            }
        }
        return false
    }

    private fun anyMatches(): Boolean {
        var anyMatches = false
        if (criteria != null) {
            for (criterion in criteria) anyMatches = anyMatches or criterion.matches(answerMatching(criterion)!!)
        }
        return anyMatches
    }

    override operator fun compareTo(that: MatchSet): Int {
        return getScore().compareTo(that.getScore())
    }
}