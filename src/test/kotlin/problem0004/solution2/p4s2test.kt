package problem0004.solution2

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import problem0004.cases

class P4S2Test : StringSpec({
    for ((input, expected) in cases) {
        "for $input should get $expected" {
            largestPalindromeProduct(input) shouldBe expected
        }
    }
})