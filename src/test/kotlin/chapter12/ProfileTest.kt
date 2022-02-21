package chapter12

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ProfileTest {
    @Test
    fun matchesNothingWhenProfileEmpty() {
        val profile = Profile()
        val question = BooleanQuestion(1, "Relocation package?")
        val criterion = Criterion(Answer(question, Bool.TRUE), Weight.DontCare)

        val result = profile.matches(criterion)

        assertFalse(result)
    }

    @Test
    fun matchesWhenProfileContainsMatchingAnswer() {
        val profile = Profile()
        val question = BooleanQuestion(1, "Relocation package?")
        val answer = Answer(question, Bool.TRUE)
        profile.add(answer)
        val criterion = Criterion(answer, Weight.Important)

        val result = profile.matches(criterion)

        assertTrue(result)
    }
}