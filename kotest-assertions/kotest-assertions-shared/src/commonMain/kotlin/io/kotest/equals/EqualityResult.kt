package io.kotest.equals

import io.kotest.assertions.print.Printers
import io.kotest.assertions.print.print

interface EqualityResult {
   fun areEqual(): Boolean

   fun details(): EqualityResultDetails

   companion object {
      fun <T> equal(
         actual: T,
         expected: T,
         equality: Equality<*>,
         printer: (T) -> String = { it.print().value }
      ): SimpleEqualityResult<T> {
         return create(equal = true, actual = actual, expected = expected, verifier = equality, printer = printer)
      }

      fun <T> notEqual(
         actual: T,
         expected: T,
         equality: Equality<*>,
         printer: (T) -> String = { it.print().value }
      ): SimpleEqualityResult<T> {
         return create(equal = false, actual = actual, expected = expected, verifier = equality, printer = printer)
      }

      private fun <T> create(
         equal: Boolean,
         actual: T,
         expected: T,
         verifier: Equality<*>,
         printer: (T) -> String
      ): SimpleEqualityResult<T> {
         return SimpleEqualityResult(
            equal = equal,
            detailsValue = SimpleEqualityResultDetail(
               actual = actual,
               expected = expected,
               printer = printer,
               explainFn = {
                  val message = if (equal) "Actual is equal to expected" else "Actual is not equal to expected"
                  return@SimpleEqualityResultDetail "$message by ${verifier.name()}"
               }
            )
         )
      }
   }
}

fun EqualityResult.areNotEqual() = !areEqual()

interface EqualityResultDetails {
   fun explain(): String

   companion object {
      fun create(reasonFn: () -> String): EqualityResultDetails {
         return object : EqualityResultDetails {
            override fun explain(): String = reasonFn()
         }
      }
   }
}

data class SimpleEqualityResult<T>(
   val equal: Boolean,
   val detailsValue: SimpleEqualityResultDetail<T>,
) : EqualityResult {
   fun withDetails(detailFn: () -> String): SimpleEqualityResult<T> {
      return copy(detailsValue = detailsValue.withDetail(detailFn))
   }

   fun withPrinter(printer: (T) -> String): SimpleEqualityResult<T> {
      return copy(detailsValue = detailsValue)
   }

   override fun areEqual(): Boolean = equal

   override fun details(): EqualityResultDetails = detailsValue
}

data class SimpleEqualityResultDetail<T>(
   val actual: T,
   val expected: T,
   val explainFn: () -> String,
   val printer: (T) -> String,
   val detailsFns: List<() -> String> = emptyList(),
) : EqualityResultDetails {
   override fun explain(): String {
      val result = StringBuilder()
      result.appendLine(explainFn())

      if (detailsFns.isNotEmpty()) {
         result.appendLine()
         detailsFns.forEach { result.appendLine(it()) }
      }

      result.appendLine()
      result.appendLine("Expected : ${printer(expected)}")
      result.appendLine("Actual   : ${printer(actual)}")
      return result.toString()
   }

   fun withDetail(detailFn: () -> String): SimpleEqualityResultDetail<T> {
      return copy(detailsFns = detailsFns + detailFn)
   }

   fun withPrinter(printer: (T) -> String): SimpleEqualityResultDetail<T> {
      return copy(printer = printer)
   }
}
