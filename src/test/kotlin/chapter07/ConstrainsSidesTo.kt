package chapter07

import org.hamcrest.Description
import org.hamcrest.Factory
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import kotlin.math.abs


class ConstrainsSidesTo(private val length: Int) : TypeSafeMatcher<Rectangle>() {
    override fun describeTo(description: Description) {
        description.appendText("both sides must be <= $length")
    }

    override fun matchesSafely(rect: Rectangle): Boolean {
        return abs(rect.origin().x - rect.opposite().x) <= length &&
                abs(rect.origin().y - rect.opposite().y) <= length
    }

    companion object {
        @Factory
        fun <T> constrainsSidesTo(length: Int): Matcher<Rectangle> {
            return ConstrainsSidesTo(length)
        }
    }
}