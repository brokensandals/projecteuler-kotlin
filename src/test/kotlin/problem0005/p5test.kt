package problem0005

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

val cases = mapOf(
    (1..10) to 2520, // projecteuler-provided example
    (1..20) to 232792560 // projecteuler question
)

class P5Test : FreeSpec({
    for (solution in solutions) {
        solution.javaClass.name - {
            for ((input, expected) in cases) {
                (solution.javaClass.simpleName + " handles $input") {
                    solution.leastCommonMultiple(input) shouldBe expected
                }
            }
        }
    }
})

