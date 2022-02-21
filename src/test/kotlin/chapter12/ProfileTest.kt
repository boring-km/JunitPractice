package chapter12

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

open class ProfileTest {

    lateinit var profile: Profile
    lateinit var questionIsThereRelocation: BooleanQuestion
    lateinit var answerThereIsRelocation: Answer
    lateinit var answerThereIsNotRelocation: Answer
    lateinit var questionReimbursesTuition: BooleanQuestion
    lateinit var answerDoesNotReimburseTuition: Answer
    lateinit var answerReimbursesTuition: Answer

    lateinit var criteria: Criteria

    @Before
    fun createCriteria() {
        criteria = Criteria()
    }

    @Before
    fun createProfile() {
        profile = Profile()
    }

    @Before
    fun createQuestionAndAnswer() { // 이렇게 2가지를 같이 생성하는 의미로 사용해도 되려나...?
        questionIsThereRelocation = BooleanQuestion(1, "Relocation package?")
        answerThereIsRelocation = Answer(questionIsThereRelocation, Bool.TRUE)
        answerThereIsNotRelocation = Answer(questionIsThereRelocation, Bool.FALSE)
        questionReimbursesTuition = BooleanQuestion(1, "Reimburses tuition?")
        answerDoesNotReimburseTuition = Answer(questionReimbursesTuition, Bool.FALSE)
        answerReimbursesTuition = Answer(questionReimbursesTuition, Bool.TRUE)
    }
}

class Profile_MatchesCriterionTest: ProfileTest() {

    @Test
    fun trueWhenMatchesSoleAnswer() {
        profile.add(answerThereIsRelocation)
        val criterion = Criterion(answerThereIsRelocation, Weight.Important)

        val result = profile.match(criterion).isMatch

        assertTrue(result)
    }

    @Test
    fun falseWhenNoMatchingAnswerContained() {
        profile.add(answerThereIsNotRelocation)
        val criterion = Criterion(answerThereIsRelocation, Weight.Important)

        val result = profile.match(criterion).isMatch

        assertFalse(result)
    }

    @Test
    fun matchesWhenContainsMultipleAnswers() {
        profile.add(answerThereIsRelocation)
        profile.add(answerDoesNotReimburseTuition)
        val criterion = Criterion(answerThereIsRelocation, Weight.Important)

        assertTrue(profile.match(criterion).isMatch)
    }

    @Test
    fun matchesWhenCriterionIsDontCare() {
        profile.add(answerDoesNotReimburseTuition)
        val criterion = Criterion(answerReimbursesTuition, Weight.DontCare)

        assertTrue(profile.match(criterion).isMatch)
    }
}

class Profile_MatchesCriteriaTest: ProfileTest() {

    @Test
    fun falseWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition)
        val criteria = Criteria()
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.Important))

        val result = profile.match(criteria).isMatch

        assertFalse(result)
    }

    @Test
    fun trueWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerThereIsRelocation)
        val criteria = Criteria()
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.Important))
        assertTrue(profile.match(criteria).isMatch)   // AAA 규칙을 안지켜도 잘 읽힌다.
    }

    @Test
    fun falseWhenAnyMustMeetCriteriaNotMet() {
        profile.add(answerThereIsRelocation)
        profile.add(answerDoesNotReimburseTuition)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.MustMatch))

        assertFalse(profile.match(criteria).isMatch)
    }
}

class Profile_ScoreTest: ProfileTest() {
    @Test
    fun zeroWhenThereAreNoMatches() {
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))

        val match: ProfileMatch = profile.match(criteria)

        assertThat(match.score, equalTo(0))
    }

    @Test
    fun 모두_일치하면_Score는_100이다() {
        criteria.add(Criterion(answerThereIsRelocation, Weight.DontCare))

        val match = profile.match(criteria)

        assertThat(match.score, equalTo(100))
    }
}