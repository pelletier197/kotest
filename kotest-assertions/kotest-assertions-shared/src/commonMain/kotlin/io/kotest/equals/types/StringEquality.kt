package io.kotest.equals.types

import io.kotest.assertions.AssertionsConfig
import io.kotest.assertions.diffLargeString
import io.kotest.common.isIntellij
import io.kotest.equals.Equality
import io.kotest.equals.EqualityResult
import io.kotest.equals.SimpleEqualityResult

open class StringEquality(
   private val ignoreCase: Boolean,
) : Equality<String> {
   private val whiteSpaces = Regex("[\n\r\t]")

   override fun name(): String = "string equality${if (ignoreCase) " (ignoring case)" else ""}"

   override fun verify(actual: String, expected: String): EqualityResult {
      val equal = { EqualityResult.equal(actual, expected, this) }
      val notEqual = { EqualityResult.notEqual(actual, expected, this) }

      return when {
         expected.equals(actual, ignoreCase = ignoreCase) -> equal()
         equalIgnoringWhitespace(actual, expected) -> notEqual()
            .withPrinter { escapeWhitespaces(it) }
            .withDetails { "Contents match, but whitespaces differ; output has been escaped to show whitespaces" }
         useDiff(expected, actual) -> diff(expected, actual, notEqual())
         else -> notEqual()
      }
   }

   fun ignoringCase(): StringEquality {
      return StringEquality(ignoreCase = true)
   }

   fun caseSensitive(): StringEquality {
      return StringEquality(ignoreCase = false)
   }

   private fun escapeWhitespaces(input: String): String {
      return input
         .replace("\n", "\\n")
         .replace("\r", "\\r")
         .replace("\t", "\\t")
   }

   private fun equalIgnoringWhitespace(actual: String, expected: String): Boolean {
      val a = whiteSpaces.replace(expected, "")
      val b = whiteSpaces.replace(actual, "")
      return a.equals(b, ignoreCase = ignoreCase)
   }

   private fun diff(expected: String, actual: String, result: SimpleEqualityResult<String>): EqualityResult {
      return when (val diff = diffLargeString(expected, actual)) {
         null -> result
         else -> result
            .withDetails {
               """
            | Showing diff:
            |
            | First    : ${diff.first}
            | Second   : ${diff.second}
            """.trimMargin()
            }
      }
   }

   private fun useDiff(expected: String, actual: String): Boolean {
      if (isIntellij()) return false
      val minSizeForDiff = AssertionsConfig.largeStringDiffMinSize
      return expected.lines().size >= minSizeForDiff
         && actual.lines().size >= minSizeForDiff &&
         AssertionsConfig.multiLineDiff != "simple"
   }
}

fun Equality.Companion.byStringEquality(
   ignoreCase: Boolean = false
) = StringEquality(
   ignoreCase = ignoreCase
)
