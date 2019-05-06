package problem0003.solution1

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import problem0003.cases

class P3S1Test : StringSpec({
    for ((input, expected) in cases) {
        "for $input should get $expected" {
            largestPrimeFactor(input) shouldBe expected
        }
    }
})