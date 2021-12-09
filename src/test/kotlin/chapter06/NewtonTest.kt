package chapter06

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.number.IsCloseTo.closeTo
import org.junit.Test
import kotlin.math.abs

class NewtonTest {
    class Newton {

        companion object {
            const val tolerance = 1e-16

            fun squareRoot(n: Double): Double {
                var approx = n
                while (abs(approx - n / approx) > tolerance * approx) {
                    approx = (n / approx + approx) / 2.0
                }
                return approx
            }
        }
    }

    @Test
    fun 제곱근_250을_구한_결과를_똑같은_값으로_다시_곱하면_250에_가까운_값이_된다() {
        // 결과를 역으로 다시 계산함
        val number = 250.0
        val result = Newton.squareRoot(number)
        assertThat(result * result, closeTo(number, Newton.tolerance))
    }
}