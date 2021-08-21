package chapter02

import org.junit.Assert.assertFalse
import org.junit.Test

class ProfileTest {
    @Test
    fun matchAnswersFalseWhenMustMatchCriteriaNotMet() {
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
}