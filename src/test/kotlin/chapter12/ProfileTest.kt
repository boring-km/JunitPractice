package chapter12

import org.junit.Assert.assertFalse
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
}