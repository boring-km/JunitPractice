package chapter02

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProfileTest {

    private lateinit var profile: Profile
    private lateinit var question: BooleanQuestion
    private lateinit var criteria: Criteria

    @Before
    fun create() {
        profile = Profile("Bull Hockey, Inc.")
        question = BooleanQuestion(1, "Got Bonuses?")
        criteria = Criteria()
    }

    @Test
    fun 반드시_맞아야_하는_기준이_맞지_않을때_정답은_false이다() {
        profile.add(Answer(question, Bool.FALSE))
        criteria.add(Criterion(Answer(question, Bool.TRUE), Weight.MustMatch))

        val matches = profile.matches(criteria)

        assertFalse(matches)
    }

    @Test
    fun 신경쓰지_않아도_되는_기준이면_정답은_true이다() {
        profile.add(Answer(question, Bool.FALSE))
        criteria.add(Criterion(Answer(question, Bool.TRUE), Weight.DontCare))

        val matches = profile.matches(criteria)

        assertTrue(matches)
    }
}