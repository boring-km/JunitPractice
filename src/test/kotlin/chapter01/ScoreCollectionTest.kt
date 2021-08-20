package chapter01

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ScoreCollectionTest {

    @Test
    fun answerArithmeticMeanOfTwoNumbers() {
        // 준비 (given)
        val collection = ScoreCollection()
        collection.add { 5 }
        collection.add { 7 }

        // 실행 (when)
        val actualResult = collection.arithmeticMean()

        // 단언 (then)
        assertThat(actualResult, equalTo(6))
    }
}