package chapter13

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ScoreCollectionTest {
   private var collection: ScoreCollection? = null
   @Before
   fun create() {
      collection = ScoreCollection()
   }

   @Test
   fun answersArithmeticMeanOfTwoNumbers() {
      collection!!.add { 5 }
      collection!!.add { 7 }
      val actualResult = collection!!.arithmeticMean()
      assertThat(actualResult, CoreMatchers.equalTo(6))
   }

   @Test(expected = IllegalArgumentException::class)
   fun throwsExceptionWhenAddingNull() {
      collection!!.add(null)
   }

   @Test
   fun answersZeroWhenNoElementsAdded() {
      assertThat(collection!!.arithmeticMean(), CoreMatchers.equalTo(0))
   }

   @Test
   fun doesNotProperlyHandleIntegerOverflow() {
      collection!!.add { Int.MAX_VALUE }
      collection!!.add { 1 }
      assertTrue(collection!!.arithmeticMean() < 0)
   }
}