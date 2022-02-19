package chapter11.util

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.logging.Logger
import java.util.regex.Pattern


class Search(
   private val inputStream: InputStream,
   private val searchString: String?,
   private val searchTitle: String?,
) {
   var error: Exception? = null
      private set
   private val matches: MutableList<Match> = ArrayList()
   private var surroundingCharacterCount = 100

   companion object {
      val LOGGER: Logger = Logger.getLogger(Search::class.java.name)
   }

   constructor(urlString: String?, searchString: String?, searchTitle: String?) : this(URL(urlString).openConnection()
      .getInputStream(), searchString, searchTitle) {
   }

   fun getMatches(): List<Match> {
      return matches
   }

   fun errored(): Boolean {
      return error != null
   }

   fun execute() {
      try {
         search()
      } catch (exc: IOException) {
         error = exc
      }
   }

   fun setSurroundingCharacterCount(count: Int) {
      surroundingCharacterCount = count
   }

   private fun addMatches(line: String, pattern: Pattern) {
      val matcher = pattern.matcher(line)
      while (matcher.find()) {
         var start = matcher.start()
         var end = matcher.end()
         start -= surroundingCharacterCount
         if (start <= 0) start = 0
         end += surroundingCharacterCount
         if (end >= line.length) end = line.length
         matches.add(Match(searchTitle,
            searchString, line.substring(start, end)))
      }
   }

   @Throws(IOException::class)
   private fun search() {
      val pattern = Pattern.compile(searchString)
      matches.clear()
      LOGGER.info("searching matches for pattern:$pattern")
      var reader: BufferedReader? = null
      try {
         reader = BufferedReader(InputStreamReader(inputStream))
         var line: String
         while (reader.readLine().also { line = it } != null) {
            addMatches(line, pattern)
         }
      } finally {
         reader?.close()
      }
   }

}