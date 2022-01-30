package io.kotest.equals.types

import io.kotest.equals.Equality
import io.kotest.equals.EqualityResult

open class RegexEqualityVerifier : Equality<Regex> {
   override fun name(): String = "regex pattern equality"

   override fun verify(actual: Regex, expected: Regex): EqualityResult {
      if (actual.pattern == expected.pattern) {
         return EqualityResult.equal(actual, expected, this)
      }

      return EqualityResult.notEqual(actual, expected, this)
   }
}

fun Equality.Companion.regexEquality(): RegexEqualityVerifier = RegexEqualityVerifier()
