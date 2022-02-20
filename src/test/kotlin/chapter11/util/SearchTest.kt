package chapter11.util

import chapter11.util.ContainsMatches.Companion.containsMatches
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.logging.Level


class SearchTest {

    companion object {
        private const val A_TITLE = "1"
    }

    @Test
    @Throws(IOException::class)
    fun testSearch() {
        val stream = streamOn("There are certain queer times and occasions "
                + "in this strange mixed affair we call life when a man "
                + "takes this whole universe for a vast practical joke, "
                + "though the wit thereof he but dimly discerns, and more "
                + "than suspects that the joke is at nobody's expense but "
                + "his own.")
        // search
        var search = Search(stream, "practical joke", A_TITLE)
        Search.LOGGER.level = Level.OFF
        search.setSurroundingCharacterCount(10)
        search.execute()
        assertFalse(search.errored())
        assertThat(search.getMatches(),
            containsMatches<Match>(arrayOf(
                Match(A_TITLE,
                    "practical joke",
                    "or a vast practical joke, though t"
                ))))
        stream.close()

        // negative
        val connection = URL("http://bit.ly/15sYPA7").openConnection()
        val inputStream: InputStream = connection.getInputStream()
        search = Search(inputStream, "smelt", A_TITLE)
        search.execute()
        assertTrue(search.getMatches().isEmpty())
        stream.close()
    }

    private fun streamOn(pageContent: String): InputStream {
        return ByteArrayInputStream(pageContent.toByteArray())
    }
}
