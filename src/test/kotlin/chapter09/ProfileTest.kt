package chapter09

import org.junit.Test

class ProfileTest {
    @Test
    fun test() {
        val profile = Profile("name")
        profile.name
    }
}