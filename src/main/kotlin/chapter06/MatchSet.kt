package chapter06

class MatchSet(private var answers: HashMap<String, Answer>, private var criteria: Criteria) {

    var score = 0

    init {
        calculateScore(criteria)
    }

    private fun calculateScore(criteria: Criteria) {
        for (criterion: Criterion in criteria) {
            if (criterion.matches(answerMatching(criterion)))
                score += criterion.weight.value
        }
    }

    private fun answerMatching(criterion: Criterion) = answers[criterion.answer.questionText]
}
