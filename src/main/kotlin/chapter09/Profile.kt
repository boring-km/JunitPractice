package chapter09

import java.util.function.Predicate
import java.util.stream.Collectors

class Profile(val name: String) {
    private val answers = AnswerCollection()


    // 회사에게 받은 질문에 대한 답변을 저장
    fun add(answer: Answer) {
        answers.add(answer)
    }

    fun getMatchSet(criteria: Criteria) : MatchSet {
        return MatchSet(answers, criteria)
    }

    override fun toString(): String {
        return name
    }

    fun find(pred: Predicate<Answer>) : List<Answer> {
        return answers.find(pred)
    }
}
