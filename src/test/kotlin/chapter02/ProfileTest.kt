package chapter02

import org.junit.Assert.*
import org.junit.Test

class ProfileTest {

    @Test
    fun 반드시_맞아야_하는_기준이_맞지_않을때_정답은_false이다() {
        val profile = Profile("Bull Hockey, Inc.")
        val question = BooleanQuestion(1, "Got Bonuses?")
        val profileAnswer = Answer(question, Bool.FALSE)
        profile.add(profileAnswer)
        val criteria = Criteria()
        val criteriaAnswer = Answer(question, Bool.TRUE)
        val criterion = Criterion(criteriaAnswer, Weight.MustMatch)

        criteria.add(criterion)

        val matches = profile.matches(criteria)

        assertFalse(matches)
    }

    @Test
    fun 신경쓰지_않아도_되는_기준이면_정답은_true이다() {
        val profile = Profile("Bull Hockey, Inc.")
        val question = BooleanQuestion(1, "Got milk?")
        val profileAnswer = Answer(question, Bool.FALSE)
        profile.add(profileAnswer)
        val criteria = Criteria()
        val criteriaAnswer = Answer(question, Bool.TRUE)
        val criterion = Criterion(criteriaAnswer, Weight.DontCare)

        criteria.add(criterion)

        val matches = profile.matches(criteria)

        assertTrue(matches)
    }
}