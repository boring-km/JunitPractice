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

    @Test
    fun throwsExceptionWhenAddingNull() {
        // Kotlin은 null을 넣을 수 없음
        collection.add{ 0 }
    }

    @Test
    fun answersZeroWhenNoElementsAdded() {
        assertThat(collection.arithmeticMean(), equalTo(0))
    }

    @Test
    fun dealsWithIntegerOverflow() {
        collection.add { Int.MAX_VALUE }
        collection.add { 1 }

        assertThat(collection.arithmeticMean(), equalTo(1073741824))
    }
}
