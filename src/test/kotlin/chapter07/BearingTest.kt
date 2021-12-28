package chapter07

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class BearingTest {

    @Test(expected = BearingOutOfRangeException::class)
    fun 음수를_방위값으로_넣으면_예외를_던진다() {
        Bearing(-1)
    }

    @Test(expected = BearingOutOfRangeException::class)
    fun 최댓값보다_1_큰_수를_방위값으로_넣으면_예외를_던진다() {
        Bearing(Bearing.MAX + 1)
    }

    @Test
    fun Bearing_값_검증() {
        assertThat(Bearing(Bearing.MAX).value, equalTo(Bearing.MAX))
    }

    @Test
    fun 방위15도와_12도_사이의_간격은_3이다() {
        assertThat(Bearing(15).angleBetween(Bearing(12)), equalTo(3))
    }

    @Test
    fun 방위12도와_15도_사이의_간격은_음수_3이다() {
        assertThat(Bearing(12).angleBetween(Bearing(15)), equalTo(-3))
    }

}
