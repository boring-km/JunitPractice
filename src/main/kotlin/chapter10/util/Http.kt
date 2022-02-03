package chapter10.util

import java.io.IOException

interface Http {
    @Throws(IOException::class)
    fun get(url: String): String
}