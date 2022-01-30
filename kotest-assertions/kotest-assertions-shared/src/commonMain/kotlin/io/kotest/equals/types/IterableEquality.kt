package io.kotest.equals.types

import io.kotest.equals.Equality
import io.kotest.equals.EqualityResult
import io.kotest.equals.areNotEqual
import io.kotest.equals.types.utils.printValues
import kotlin.math.exp
import kotlin.math.min

open class IterableEquality(
   private val strictNumberEquality: Boolean,
   private val ignoreCase: Boolean,
   private val ignoreOrder: Boolean,
) : Equality<Iterable<*>> {
   override fun name(): String = "iterable equality${if (ignoreOrder) " (ignoring order)" else ""}"

   override fun verify(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
      return when {
         actual is Set<*> && expected is Set<*> -> areSetsEqual(actual, expected)
         else -> {
            val typeEquality = typeCompatibilityEquality(actual, expected)
            return when {
               typeEquality.areNotEqual() -> return typeEquality
               ignoreOrder -> areEqualIgnoringOrder(actual, expected)
               else -> areEqualInOrder(actual.toList(), expected.toList())
            }
         }
      }
   }

   private fun areEqualInOrder(actual: List<*>, expected: List<*>): EqualityResult {
      val extraItems = if (actual.size > expected.size) actual.subList(expected.size, actual.size) else emptyList()
      val missingItems = if (expected.size > actual.size) expected.subList(actual.size, expected.size) else emptyList()
      val equality = objectEquality()
      val differentIndexes = (0 until min(actual.size, expected.size)).filter {
         equality.verify(actual[it], expected[it]).areNotEqual()
      }

      if (extraItems.isEmpty() && missingItems.isEmpty() && differentIndexes.isEmpty()) {
         return EqualityResult.equal(actual, expected, this)
      }

      val messages = listOfNotNull(
         if (differentIndexes.isNotEmpty()) "Elements differ by ${equality.name()} at indexes ${
            printValues(differentIndexes)
         }" else null,
         if (extraItems.isNotEmpty()) "There are ${extraItems.size} extra items: ${printValues(extraItems)}" else null,
         if (missingItems.isNotEmpty()) "${missingItems.size} items are missing: ${printValues(missingItems)}" else null,
      )

      return EqualityResult.notEqual(actual, expected, this).withDetails {
         "There ${if (messages.size > 1) "are ${messages.size} issues" else "is ${messages.size} issue"} with the iterables:\n${
            messages.joinToString(
               separator = "\n "
            )
         }"
      }
   }

   private fun areEqualIgnoringOrder(actual: Iterable<*>, expected: Iterable<*>): EqualityResult {
      val itemEqualityVerifier = objectEquality()
      return areEqualIgnoringOrder(
         actual = actual.toList(),
         expected = expected.toList(),
         iterableContainFunction = { list, item ->
            list.any { itemEqualityVerifier.verify(it, item).areEqual() }
         })
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


   private fun areSetsEqual(actual: Set<*>, expected: Set<*>): EqualityResult {
      val itemEqualityVerifier = objectEquality()
      return areEqualIgnoringOrder(actual = actual, expected = expected, iterableContainFunction = { set, item ->
         // Contained as is.
         // Best performance for most implementation, as it will usually either be a hash table or a binary tree.
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
         if (extra.isEmpty()) null else "There are ${extra.size} extra items: ${printValues(extra)}",
         if (missing.isEmpty()) null else "${missing.size} items are missing: ${printValues(missing)}",
      )

      return EqualityResult.notEqual(actual, expected, this).withDetails {
         (listOf("Iterable contents are not equal by ${name()}") + details).joinToString(
            separator = "\n"
         )
      }
   }

   protected fun objectEquality(): Equality<Any?> = ObjectEqualsEquality(
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
   ): IterableEquality {
      return IterableEquality(
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
): IterableEquality = IterableEquality(
   strictNumberEquality = strictNumberEquality,
   ignoreCase = ignoreCase,
   ignoreOrder = ignoreOrder,
)
