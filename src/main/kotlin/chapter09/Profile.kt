package chapter09

import java.util.function.Predicate
import java.util.stream.Collectors

class Profile(val name: String) {
    private val answers = HashMap<String, Answer>()
    var score: Int = 0
        private set


    // 회사에게 받은 질문에 대한 답변을 저장
    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun matches(criteria: Criteria) : Boolean {
        val matchSet = MatchSet(answers, criteria)
        score = matchSet.score
        return matchSet.matches()
    }

    override fun toString(): String {
        return name
    }

    fun find(pred: Predicate<Answer>) : List<Answer> {
        return answers.values.stream()
            .filter(pred)
            .collect(Collectors.toList())
    }
}
