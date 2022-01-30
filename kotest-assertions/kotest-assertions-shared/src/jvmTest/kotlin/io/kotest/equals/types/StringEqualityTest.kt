package io.kotest.equals.types

import io.kotest.core.spec.style.FunSpec
import io.kotest.equals.Equality
import io.kotest.matchers.shouldBe

class StringEqualityTest : FunSpec({
   test("string equality should highlight line break diffs") {
      val result = Equality
         .byStringEquality()
         .verify("foo\nbar\r", "\r\nfoo\nbar\r\n")
      result.areEqual() shouldBe false
      result.details().explain() shouldBe """
         | Contents match by string equality, but whitespaces differ; output has been escaped to show whitespaces
         | Expected : \r\nfoo\nbar\r\n
         | Actual   : foo\nbar\r
         """.trimMargin()
   }
})
