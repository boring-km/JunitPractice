package chapter09

import java.util.function.Predicate
import java.util.stream.Collectors

class Profile(private val _name: String) {
    private val answers = HashMap<String, Answer>()
    var score: Int = 0
        private set

    val name
        get() = _name


    // 회사에게 받은 질문에 대한 답변을 저장
    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun matches(criteria: Criteria) : Boolean {
        calculateScore(criteria)    // 점수 계산

        if (doesNotMeetAnyMustMatchCriterion(criteria)) // 특정 조건에 걸리면 false
            return false
        return anyMatches(criteria) // 조건이 맞는 다른 경우를 찾기
    }

    private fun doesNotMeetAnyMustMatchCriterion(criteria: Criteria): Boolean {

        for (criterion: Criterion in criteria) {
            val match = criterion.matches(answerMatching(criterion))
            if (!match && criterion.weight == Weight.MustMatch) {
                return true
            }
        }
        return false
    }

    private fun calculateScore(criteria: Criteria) {
        score = 0
        for (criterion: Criterion in criteria) {
            if (criterion.matches(answerMatching(criterion)))
                score += criterion.weight.value
        }
    }

    private fun anyMatches(criteria: Criteria) : Boolean {
        var anyMatches = false
        for (criterion: Criterion in criteria) {
            anyMatches = anyMatches or criterion.matches(answerMatching(criterion))
        }
        return anyMatches
    }

    private fun answerMatching(criterion: Criterion) = answers[criterion.answer.questionText]

    override fun toString(): String {
        return name
    }

    fun find(pred: Predicate<Answer>) : List<Answer> {
        return answers.values.stream()
            .filter(pred)
            .collect(Collectors.toList())
    }
}
