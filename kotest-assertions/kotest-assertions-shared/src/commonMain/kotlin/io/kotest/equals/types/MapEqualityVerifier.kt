package io.kotest.equals.types

import io.kotest.equals.Equality
import io.kotest.equals.EqualityResult
import io.kotest.equals.areNotEqual
import io.kotest.equals.types.utils.printValues

open class MapEqualityVerifier(
   private val strictNumberEquality: Boolean,
   private val ignoreCase: Boolean,
   private val ignoreOrder: Boolean,
) : Equality<Map<*, *>> {
   override fun name(): String = "map equality"

   override fun verify(actual: Map<*, *>, expected: Map<*, *>): EqualityResult {
      val equal = { EqualityResult.equal(actual, expected, this) }
      val notEqual = { EqualityResult.notEqual(actual, expected, this) }

      val actualKeys = actual.keys
      val expectedKeys = expected.keys

      val keysMissing = expectedKeys.subtract(actualKeys)
      val extraKeys = actualKeys.subtract(expectedKeys)
      val commonKeys = actualKeys.intersect(expectedKeys)

      val valuesVerifier = ObjectEqualsEquality<Any?>(
         strictNumberEquality = strictNumberEquality,
         ignoreCase = ignoreCase,
         ignoreOrder = ignoreOrder,
      )

      val differentValues = commonKeys.mapNotNull { key ->
         val actualValue = actual[key]
         val expectedValue = expected[key]
         return@mapNotNull valuesVerifier.verify(actualValue, expectedValue).takeIf { it.areNotEqual() }?.let {
            Pair(key, it)
         }
      }

      if (keysMissing.isEmpty() && extraKeys.isEmpty() && differentValues.isEmpty()) {
         return equal()
      }

      val details = listOfNotNull(
         if (keysMissing.isEmpty()) null else "Some keys are missing: ${printValues(keysMissing)}",
         if (extraKeys.isEmpty()) null else "Some keys should not be there: ${printValues(keysMissing)}",
         if (differentValues.isEmpty()) null else "Some entries have different values: ${
            printValues(differentValues.map {
               """
               At key '${it.first}': ${it.second.details().explain()}

               """.trimIndent()
            })
         }",
      )

      return notEqual().withDetails {
         (listOf("Map contents are not equal by ${name()})") + details).joinToString(separator = "\n")
      }
   }

   protected fun objectEqualityVerifier(): Equality<Any?> = ObjectEqualsEquality(
      strictNumberEquality = strictNumberEquality,
      ignoreCase = ignoreCase,
      ignoreOrder = ignoreOrder,
   )

   fun withStrictNumberEquality() = copy(strictNumberEquality = true)
   fun withoutStrictNumberEquality() = copy(strictNumberEquality = false)
   fun ignoringCase() = copy(ignoreCase = true)
   fun caseSensitive() = copy(ignoreCase = false)
   fun ignoringOrder() = copy(ignoreOrder = true)
   fun orderSensitive() = copy(ignoreOrder = false)

   private fun copy(
      strictNumberEquality: Boolean = this.strictNumberEquality,
      ignoreCase: Boolean = this.ignoreCase,
      ignoreOrder: Boolean = this.ignoreOrder,
   ): MapEqualityVerifier {
      return MapEqualityVerifier(
         strictNumberEquality = strictNumberEquality,
         ignoreCase = ignoreCase,
         ignoreOrder = ignoreOrder,
      )
   }
}

fun Equality.Companion.byMapEquality(
   strictNumberEquality: Boolean = false,
   ignoreCase: Boolean = false,
   ignoreOrder: Boolean = false,
): MapEqualityVerifier = MapEqualityVerifier(
   strictNumberEquality = strictNumberEquality,
   ignoreCase = ignoreCase,
   ignoreOrder = ignoreOrder,
)
