package chapter09

import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse

class MatchSetTest {

    private lateinit var questionReimbursesTuition: BooleanQuestion
    private lateinit var answerReimbursesTuition: Answer    // 수업을 상환한다고 대답
    private lateinit var answerDoesNotReimburseTuition: Answer  // 수업 상환 안한다고 대답
    private lateinit var criteria: Criteria
    private lateinit var answers: AnswerCollection


    @Before
    fun createCriteria() {
        criteria = Criteria()
    }

    @Before
    fun createQuestionAndAnswers() {
        questionReimbursesTuition = BooleanQuestion(1, "Reimburses tuition?")   // 정답이 true false인 질문
        answerReimbursesTuition = Answer(questionReimbursesTuition, Bool.TRUE)  // 상환을 한다고 하면 정답
        answerDoesNotReimburseTuition = Answer(questionReimbursesTuition, Bool.FALSE)   // 상환을 안한다고 하면 오답
    }

    private fun add(answer: Answer) {
        answers.add(answer)
    }

    private fun createMatchSet(): MatchSet {
        return MatchSet(answers, criteria)
    }

    @Test
    fun MustMatch_조건에_맞지_않는_answer이면_match_여부는_false이다() {
        add(answerDoesNotReimburseTuition)
        criteria.add(Criterion(answerReimbursesTuition, Weight.MustMatch))
        assertFalse(createMatchSet().matches())
    }

}