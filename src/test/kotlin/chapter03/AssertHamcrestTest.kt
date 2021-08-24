package chapter03

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.number.IsCloseTo.closeTo
import org.junit.Before
import org.junit.Test

class AssertHamcrestTest {

    private lateinit var account: Account

    @Before
    fun initialize() {
        account = Account()
    }

    @Test
    fun 부동소수점을_closeTo로_비교하자() {
        assertThat(2.32 * 3, closeTo(6.96, 0.0005))
    }

    @Test
    fun testWithWorthlessAssertionComment() {
        account.deposit(50);
        assertThat("account balance is 100", account.getBalance(), equalTo(50));
    }
}