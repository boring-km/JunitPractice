package chapter03
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertThrows
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class ExceptionTest {

    private lateinit var account: Account

    @Before
    fun initialize() {
        account = Account()
    }

    @Test(expected = InsufficientFundsException::class)
    fun annotation을_사용하는_방식() {
        account.withdraw(100)
        fail()
    }

    @Test
    fun try_catch와_fail을_사용하는_방식() {
        try {
            account.withdraw(100)
            fail()
        } catch (expected: InsufficientFundsException){
            assertThat(expected.message, equalTo("계좌에 인출할 돈이 부족합니다."))
        }
    }

    // 'none(): ExpectedException!' is deprecated. Deprecated in Java
    // JUnit 4.13 부터는 다른 방식으로...
    @Test
    fun 새로운_ExpectedException_방식() {
        assertThrows("계좌에 인출할 돈이 부족합니다.", InsufficientFundsException::class.java) { account.withdraw(100) }
    }
}