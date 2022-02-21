package chapter12

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProfileTest {

    private lateinit var profile: Profile
    private lateinit var questionIsThereRelocation: BooleanQuestion
    private lateinit var answerThereIsRelocation: Answer

    @Before
    fun createProfile() {
        profile = Profile()
    }

    @Before
    fun createQuestionAndAnswer() { // 이렇게 2가지를 같이 생성하는 의미로 사용해도 되려나...?
        questionIsThereRelocation = BooleanQuestion(1, "Relocation package?")
        answerThereIsRelocation = Answer(questionIsThereRelocation, Bool.TRUE)
    }

    @Test
    fun matchesNothingWhenProfileEmpty() {
        val criterion = Criterion(answerThereIsRelocation, Weight.DontCare)

        val result = profile.matches(criterion)

        assertFalse(result)
    }

    @Test
    fun matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation)
        val criterion = Criterion(answerThereIsRelocation, Weight.Important)

        val result = profile.matches(criterion)

        assertTrue(result)
    }
}