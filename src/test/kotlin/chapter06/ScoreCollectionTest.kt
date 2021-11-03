package chapter06

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import kotlin.jvm.Throws

class ScoreCollectionTest {

    private lateinit var collection: ScoreCollection

    @Before
    fun setUp() {
        collection = ScoreCollection()
    }

    @Throws(IllegalArgumentException::class)
    @Test
    fun throwsExceptionWhenAddingNull() {
        // java의 경우라면 null 체크를 안하니까 일부러 Kotlin을 안 쓰고 문제가 될만한 상황을 만들어본다.
        collection.add(null)
    }

    @Test
    fun answersZeroWhenNoElementsAdded() {
        assertThat(collection.arithmeticMean(), equalTo(0))
    }
}
