package io.kotest.equals.types.utils

import kotlin.math.max

internal fun printValues(collection: Collection<*>, maxElements: Int = 10): String {
   val extra = collection.size - maxElements
   val actualMax = max(1, maxElements)
   return collection.take(actualMax).joinToString(
      prefix = "[",
      separator = ", ",
      postfix = "${if (extra > 0) "... and $extra more" else ""}]"
   )
}
