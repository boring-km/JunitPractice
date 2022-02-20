package chapter11.util

import chapter11.util.ContainsMatches.Companion.containsMatches
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.logging.Level


class SearchTest {

    private lateinit var stream: InputStream

    companion object {
        private const val A_TITLE = "1"
    }

    @Before
    fun turnOFfLogging() {
        Search.LOGGER.level = Level.OFF
    }

    @After
    @Throws(IOException::class)
    fun closeResources() {
        stream.close()
    }

    @Test
    fun testSearch() {
        stream = streamOn("rest of text here" +
                "1234567890search term1234567890" +
                "more rest of text")
        val search = Search(stream, "search term", A_TITLE)
        search.setSurroundingCharacterCount(10)

        search.execute()

        assertThat(search.getMatches(), containsMatches<Match>(arrayOf(
                Match(A_TITLE,
                    "search term",
                    "1234567890search term1234567890"
                ))))

    }

    private fun streamOn(pageContent: String): InputStream {
        return ByteArrayInputStream(pageContent.toByteArray())
    }

    @Test
    fun noMatchesReturnedWhenSearchStringNotInContent() {
        stream = streamOn("any text")
        val search = Search(stream, "text that doesn't match", A_TITLE)

        search.execute()

        assertTrue(search.getMatches().isEmpty())
    }

    @Test
    fun returnsErroredWhenUnableToReadStream() {
        stream = createStreamThrowingErrorWhenRead()
        val search = Search(stream, "", "")

        search.execute()

        assertTrue(search.errored())
    }

    private fun createStreamThrowingErrorWhenRead(): InputStream {
        return object : InputStream() {
            override fun read(): Int {
                throw IOException()
            }
        }
    }

    @Test
    fun erroredReturnsFalseWhenReadSucceeds() {
        stream = streamOn("")
        val search = Search(stream, "", "")

        search.execute()

        assertFalse(search.errored())
    }
}
