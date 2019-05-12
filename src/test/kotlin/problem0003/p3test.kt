package problem0003

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

val cases = mapOf(
    13195L to 29L, // given example
    600851475143L to 6857L // Euler problem
)

class P3Test : FreeSpec({
    for (solution in solutions) {
        solution.javaClass.name - {
            for ((input, expected) in cases) {
                (solution.javaClass.simpleName + " handles $input") {
                    solution.largestPrimeFactor(input) shouldBe expected
                }
            }
        }
    }
})
