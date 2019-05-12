package problem0001

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

val cases = mapOf(
    10 to 23,
    1000 to 233168,
    1000000000 to 631780268
)

class P1Test : FreeSpec({
    for (solution in solutions) {
        solution.javaClass.name - {
            for ((input, expected) in cases) {
                (solution.javaClass.simpleName + " handles $input") {
                    solution.sumMultiplesOf3And5(input) shouldBe expected
                }
            }
        }
    }
})
