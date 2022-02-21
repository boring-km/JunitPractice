package chapter12

import org.junit.Assert.*
import org.junit.Test

class AnswerTest {
    @Test
    fun matchAgainstNullAnswerReturnsFalse() {
        assertFalse(Answer(BooleanQuestion(0, ""), Bool.TRUE).match(null))
    }
}