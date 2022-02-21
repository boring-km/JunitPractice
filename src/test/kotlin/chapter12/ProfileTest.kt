package chapter12

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ProfileTest {

    private lateinit var profile: Profile
    private lateinit var questionIsThereRelocation: BooleanQuestion
    private lateinit var answerThereIsRelocation: Answer
    private lateinit var answerThereIsNotRelocation: Answer
    private lateinit var questionReimbursesTuition: BooleanQuestion
    private lateinit var answerDoesNotReimburseTuition: Answer
    private lateinit var answerReimbursesTuition: Answer

    private lateinit var criteria: Criteria

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

    @Test
    fun matchesWhenProfileContainsMatchingAnswer() {
        profile.add(answerThereIsRelocation)
        val criterion = Criterion(answerThereIsRelocation, Weight.Important)
        criteria.add(criterion)

        val result = profile.match(criteria).isMatch

        assertTrue(result)
    }

    @Test
    fun doesNotMatchWhenNoMatchingAnswer() {
        profile.add(answerThereIsNotRelocation)
        val criterion = Criterion(answerThereIsRelocation, Weight.Important)
        criteria.add(criterion)

        val result = profile.match(criteria).isMatch

        assertFalse(result)
    }

    @Test
    fun doesNotMatchWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerDoesNotReimburseTuition)
        val criteria = Criteria()
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.Important))

        val result = profile.match(criteria).isMatch

        assertFalse(result)
    }

    @Test
    fun matchesWhenAnyOfMultipleCriteriaMatch() {
        profile.add(answerThereIsRelocation)
        val criteria = Criteria()
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.Important))
        assertTrue(profile.match(criteria).isMatch)   // AAA 규칙을 안지켜도 잘 읽힌다.
    }

    @Test
    fun doesNotMatchWhenAnyMustMeetCriteriaNotMet() {
        profile.add(answerThereIsRelocation)
        profile.add(answerDoesNotReimburseTuition)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.MustMatch))

        assertFalse(profile.match(criteria).isMatch)
    }

    @Test
    fun matchesWhenCriterionIsDontCare() {
        profile.add(answerDoesNotReimburseTuition)
        val criterion = Criterion(answerReimbursesTuition, Weight.DontCare)
        criteria.add(criterion)

        assertTrue(profile.match(criteria).isMatch)
    }

    @Test
    fun scoreIsZeroWhenThereAreNoMatches() {
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