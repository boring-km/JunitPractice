package chapter09

import java.util.function.Predicate
import java.util.stream.Collectors

class AnswerCollection {
    private val answers = HashMap<String, Answer>()

    fun add(answer: Answer) {
        answers[answer.questionText] = answer
    }

    fun answerMatching(criterion: Criterion): Answer? {
        return answers[criterion.answer.questionText]
    }

    fun find(pred: Predicate<Answer>) : List<Answer> {
        return answers.values.stream()
            .filter(pred)
            .collect(Collectors.toList())
    }
}
