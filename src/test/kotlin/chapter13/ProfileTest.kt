package chapter13

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class ProfileTest {
    private var profile: Profile? = null

    @Before
    fun createProfile() {
        profile = Profile("")
    }

    private fun ids(answers: Collection<Answer>): IntArray? {
        return answers.stream()
            .mapToInt { a: Answer -> a.question.id }.toArray()
    }

    @Test
    fun findsAnswersBasedOnPredicate() {
        profile!!.add(Answer(BooleanQuestion(1, "1"), Bool.FALSE))
        profile!!.add(Answer(PercentileQuestion(2, "2", arrayOf()), 0))
        profile!!.add(Answer(PercentileQuestion(3, "3", arrayOf()), 0))
        val answers: List<Answer> = profile!!.find { a: Answer ->
            a.question.javaClass === PercentileQuestion::class.java
        }
        assertThat(ids(answers), equalTo(intArrayOf(2, 3)))
    }
}