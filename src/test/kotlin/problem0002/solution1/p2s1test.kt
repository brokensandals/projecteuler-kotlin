package problem0002.solution1

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import problem0002.cases

class P2S1Test : StringSpec({
    for ((input, expected) in cases) {
        "for $input should get $expected" {
            sumOfEvenFib(input) shouldBe expected
        }
    }
})