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
    @Throws(IOException::class)
    fun testSearch() {
        stream = streamOn("There are certain queer times and occasions "
                + "in this strange mixed affair we call life when a man "
                + "takes this whole universe for a vast practical joke, "
                + "though the wit thereof he but dimly discerns, and more "
                + "than suspects that the joke is at nobody's expense but "
                + "his own.")
        val search = Search(stream, "practical joke", A_TITLE)
        search.setSurroundingCharacterCount(10)
        search.execute()
        assertThat(search.getMatches(), containsMatches<Match>(arrayOf(
                Match(A_TITLE,
                    "practical joke",
                    "or a vast practical joke, though t"
                ))))

    }

    private fun streamOn(pageContent: String): InputStream {
        return ByteArrayInputStream(pageContent.toByteArray())
    }

    @Test
    @Throws(MalformedURLException::class, IOException::class)
    fun noMatchesReturnedWhenSearchStringNotInContent() {
        val connection = URL("http://bit.ly/15sYPA7").openConnection()
        stream = connection.getInputStream()
        val search = Search(stream, "smelt", A_TITLE)
        search.execute()
        assertTrue(search.getMatches().isEmpty())
    }
}
