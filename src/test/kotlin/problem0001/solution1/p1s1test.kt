package problem0001.solution1

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import problem0001.cases

class P1S1Test : StringSpec({
    for ((input, expected) in cases) {
        "for $input should get $expected" {
            sumMultiplesOf3And5(input) shouldBe expected
        }
    }
})