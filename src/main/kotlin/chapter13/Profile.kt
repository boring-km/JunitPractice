package chapter13

import java.util.function.Predicate
import java.util.stream.Collectors

class Profile(private var id: String) {
    private var answers: MutableMap<String, Answer> = HashMap()

    fun getId(): String {
        return id
    }

    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun getMatchSet(criteria: Criteria?): MatchSet {
        return MatchSet(id, answers, criteria!!)
    }

    override fun toString(): String {
        return id
    }

    fun find(pred: Predicate<Answer>): MutableList<Answer> {
        return answers.values.stream()
            .filter(pred)
            .collect(Collectors.toList())
    }
}
