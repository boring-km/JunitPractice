package chapter01

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ScoreCollectionTest {

    @Test
    fun answerArithmeticMeanOfTwoNumbers() {
        // 준비 (arange, given)
        val collection = ScoreCollection()
        collection.add { 5 }
        collection.add { 7 }

        // 실행 (act, when)
        val actualResult = collection.arithmeticMean()

        // 단언 (assert, then)
        assertThat(actualResult, equalTo(6))
    }
}