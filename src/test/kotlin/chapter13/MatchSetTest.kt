package chapter13

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class MatchSetTest {
    private lateinit var criteria: Criteria
    private lateinit var questionReimbursesTuition: Question
    private lateinit var answerReimbursesTuition: Answer
    private lateinit var answerDoesNotReimburseTuition: Answer
    private lateinit var questionIsThereRelocation: Question
    private lateinit var answerThereIsRelocation: Answer
    private lateinit var answerThereIsNoRelocation: Answer
    private lateinit var questionOnsiteDaycare: Question
    private lateinit var answerNoOnsiteDaycare: Answer
    private lateinit var answerHasOnsiteDaycare: Answer
    private lateinit var answers: MutableMap<String, Answer>
    @Before
    fun createAnswers() {
        answers = HashMap()
    }

    @Before
    fun createCriteria() {
        criteria = Criteria()
    }

    @Before
    fun createQuestionsAndAnswers() {
        questionIsThereRelocation = BooleanQuestion(1, "Relocation package?")
        answerThereIsRelocation = Answer(questionIsThereRelocation, Bool.TRUE)
        answerThereIsNoRelocation = Answer(questionIsThereRelocation, Bool.FALSE)
        questionReimbursesTuition = BooleanQuestion(1, "Reimburses tuition?")
        answerReimbursesTuition = Answer(questionReimbursesTuition, Bool.TRUE)
        answerDoesNotReimburseTuition = Answer(questionReimbursesTuition, Bool.FALSE)
        questionOnsiteDaycare = BooleanQuestion(1, "Onsite daycare?")
        answerHasOnsiteDaycare = Answer(questionOnsiteDaycare, Bool.TRUE)
        answerNoOnsiteDaycare = Answer(questionOnsiteDaycare, Bool.FALSE)
    }

    private fun add(answer: Answer?) {
        answers[answer!!.questionText] = answer
    }

    private fun createMatchSet(): MatchSet {
        return MatchSet("", answers, criteria)
    }

    @Test
    fun matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        add(answerDoesNotReimburseTuition)
        criteria.add(
            Criterion(answerReimbursesTuition, Weight.MustMatch)
        )
        assertFalse(createMatchSet().matches())
    }

    @Test
    fun matchAnswersTrueForAnyDontCareCriteria() {
        add(answerDoesNotReimburseTuition)
        criteria.add(
            Criterion(answerReimbursesTuition, Weight.DontCare)
        )
        assertTrue(createMatchSet().matches())
    }

    @Test
    fun matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
        add(answerThereIsRelocation)
        add(answerDoesNotReimburseTuition)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.Important))
        assertTrue(createMatchSet().matches())
    }

    @Test
    fun matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
        add(answerThereIsNoRelocation)
        add(answerDoesNotReimburseTuition)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.Important))
        assertFalse(createMatchSet().matches())
    }

    @Test
    fun scoreIsZeroWhenThereAreNoMatches() {
        add(answerThereIsNoRelocation)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        assertThat(createMatchSet().getScore(), CoreMatchers.equalTo(0))
    }

    @Test
    fun scoreIsCriterionValueForSingleMatch() {
        add(answerThereIsRelocation)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        assertThat(createMatchSet().getScore(), CoreMatchers.equalTo(Weight.Important.value))
    }

    @Test
    fun scoreAccumulatesCriterionValuesForMatches() {
        add(answerThereIsRelocation)
        add(answerReimbursesTuition)
        add(answerNoOnsiteDaycare)
        criteria.add(Criterion(answerThereIsRelocation, Weight.Important))
        criteria.add(Criterion(answerReimbursesTuition, Weight.WouldPrefer))
        criteria.add(Criterion(answerHasOnsiteDaycare, Weight.VeryImportant))
        val expectedScore = Weight.Important.value + Weight.WouldPrefer.value
        assertThat(createMatchSet().getScore(), CoreMatchers.equalTo(expectedScore))
    }

    // TODO: missing functionality--what if there is no matching profile answer for a criterion?
    @Test
    fun sortsByScore() {
        add(Answer(questionReimbursesTuition, Bool.FALSE))
        criteria.add(
            Criterion(
                Answer(questionReimbursesTuition, Bool.TRUE),
                Weight.Important
            )
        )
        val smallerSet = object : MatchSet("1", null, null) {
            override fun getScore(): Int {
                return 1
            }
        }
        val largerSet = object : MatchSet("2", null, null) {
            override fun getScore(): Int {
                return 2
            }
        }
        assertTrue(smallerSet < largerSet)
    }
}