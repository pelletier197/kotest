package io.kotest.equals.types

import io.kotest.equals.Equality
import io.kotest.equals.EqualityResult
import io.kotest.equals.areNotEqual
import io.kotest.equals.types.utils.printValues

open class IterableEqualityVerifier(
   private val strictNumberEquality: Boolean,
   private val ignoreCase: Boolean,
   private val ignoreOrder: Boolean,
) : Equality<Iterable<*>> {
   override fun name(): String = "iterable equality"

   override fun verify(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
      return when {
         actual is Set<*> && expected is Set<*> -> areSetsEqual(actual, expected)
         ignoreOrder -> areEqualIgnoringOrder(actual, expected)
         else -> areEqualInOrder(actual, expected)
      }
   }

   private fun areEqualInOrder(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
      TODO("Not yet implemented")
   }

   private fun areEqualIgnoringOrder(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
      val itemEqualityVerifier = objectEqualityVerifier()
      val contentResult = areEqualIgnoringOrder(
         actual = actual.toList(),
         expected = expected.toList(),
         iterableContainFunction = { list, item ->
            list.any { itemEqualityVerifier.verify(it, item).areEqual() }
         })

      val typeEqualityResult = typeCompatibilityEquality(actual, expected)

      if (contentResult.areEqual() && typeEqualityResult.areEqual()) {
         return EqualityResult.equal(actual, expected, this)
      }

      if (!typeEqualityResult.areEqual()) {
         return typeEqualityResult
      }

      return contentResult
   }

   private fun typeCompatibilityEquality(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
      val notEqual = { EqualityResult.notEqual(actual, expected, this) }
      val tag =
         { "${actual::class.simpleName ?: actual::class} with ${expected::class.simpleName ?: expected::class}\n" }
      return when {
         actual is Set<*> && expected !is Set<*> || actual !is Set<*> && expected is Set<*>
         -> notEqual().withDetails { "Set can be compared only to Set: ${tag()}" }
         else -> EqualityResult.equal(actual, expected, this)
      }
   }


//   private fun areTypesCompatible(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
//      val tag = "${actual::class.simpleName ?: actual::class} with ${expected::class.simpleName ?: expected::class}\n"
//      val detailErrorMessage = when {
//         actual is Set<*> || expected is Set<*> -> "Set can be compared only to Set\nMay not compare $tag"
//         (actual is Collection || actual is Array<*>) || (expected is Collection || expected is Array<*>) -> "${IterableEq.trigger} typed contract\nMay not compare $tag"
//         else -> "${IterableEq.trigger} promiscuous iterators\nMay not compare $tag"
//      }
//      return failure(Expected(Printed("*")), Actual(Printed("*")), detailErrorMessage)
//   }


   // This implementation ignores set item order as for set's equal implementation.
   private fun areSetsEqual(actual: Set<*>, expected: Set<*>): EqualityResult {
      val itemEqualityVerifier = objectEqualityVerifier()
      return areEqualIgnoringOrder(actual = actual, expected = expected, iterableContainFunction = { set, item ->
         // Contained as is. Best performance.
         set.contains(item)
            // Any item is equal according to the equality verifier
            || expected.any { itemEqualityVerifier.verify(item, it).areEqual() }
      })
   }

   private fun <T, I : Iterable<T>> areEqualIgnoringOrder(
      actual: I,
      expected: I,
      iterableContainFunction: (iterable: Iterable<T>, item: T) -> Boolean
   ): EqualityResult {
      val missing = actual.filterNot { item -> iterableContainFunction(expected, item) }
      val extra = expected.filterNot { item -> iterableContainFunction(actual, item) }

      if (missing.isEmpty() && extra.isEmpty()) {
         return EqualityResult.equal(actual, expected, this)
      }

      val details = listOfNotNull(
         if (missing.isEmpty()) null else "Some entries are missing: ${printValues(missing)}",
         if (extra.isEmpty()) null else "Some keys should not be there: ${printValues(extra)}",
      )

      return EqualityResult.notEqual(actual, expected, this).withDetails {
         (listOf("Iterable contents are not equal by ${name()} (ignoring order))") + details).joinToString(
            separator = "\n"
         )
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
   ): IterableEqualityVerifier {
      return IterableEqualityVerifier(
         strictNumberEquality = strictNumberEquality,
         ignoreCase = ignoreCase,
         ignoreOrder = ignoreOrder,
      )
   }
}

fun Equality.Companion.byIterableEquality(
   strictNumberEquality: Boolean = false,
   ignoreCase: Boolean = false,
   ignoreOrder: Boolean = false,
): IterableEqualityVerifier = IterableEqualityVerifier(
   strictNumberEquality = strictNumberEquality,
   ignoreCase = ignoreCase,
   ignoreOrder = ignoreOrder,
)
