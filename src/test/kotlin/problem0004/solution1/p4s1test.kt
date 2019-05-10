package problem0004.solution1

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import problem0004.cases

class P4S1Test : StringSpec({
    for ((input, expected) in cases) {
        "for $input should get $expected" {
            largestPalindromeProduct(input) shouldBe expected
        }
    }
})