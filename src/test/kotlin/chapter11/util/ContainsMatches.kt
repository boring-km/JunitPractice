package chapter11.util

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class ContainsMatches(private val expected: Array<Match>) : TypeSafeMatcher<List<Match>>() {
    override fun describeTo(description: Description) {
        description.appendText("<$expected>")
    }

    private fun equals(expected: Match, actual: Match): Boolean {
        return expected.searchString == actual.searchString && expected.surroundingContext == actual.surroundingContext
    }

    override fun matchesSafely(actual: List<Match>): Boolean {
        if (actual.size != expected.size) return false
        for (i in expected.indices) if (!equals(expected[i], actual[i])) return false
        return true
    }

    companion object {
        @Factory
        fun <T> containsMatches(expected: Array<Match>): Matcher<List<Match>> {
            return ContainsMatches(expected)
        }
    }
}