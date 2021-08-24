package chapter03

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class AssertHamcrestTest {
    @Test
    fun 부동소수점을_equalTo로_비교하면_틀린다() {
        assertThat(2.32 * 3, equalTo(6.96))
    }
}