package chapter06

import chapter09.*
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue


class ProfileTest {

    private lateinit var profile: Profile

    @Before
    fun createProfile() {
        profile = Profile("Kangmin Jin")
    }

    @Test
    fun findAnswers() {
        val dataSize = 5000
        for (i in 0 until dataSize) {
            profile.add(Answer(BooleanQuestion(i, i.toString()), Bool.FALSE))
        }
        profile.add(Answer(
            PercentileQuestion(
                dataSize, dataSize.toString(), arrayOf()), 0
            )
        )
        val numberOfTimes = 1000

        val elapsedMs = run(numberOfTimes) {
            profile.find { a ->
                a.question.javaClass == PercentileQuestion::class.java
            }
        }
        assertTrue(elapsedMs < 1000)
    }

    private fun run(times: Int, func: Runnable): Long {
        val start = System.nanoTime()
        for (i in 0 until times) func.run()
        val stop = System.nanoTime()
        return (stop - start) / 1000000
    }
}