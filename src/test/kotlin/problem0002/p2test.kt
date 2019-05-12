package problem0002

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

val cases = mapOf(
    89 to 44,
    4000000 to 4613732
)

class P2Test : FreeSpec({
    for (solution in solutions) {
        solution.javaClass.name - {
            for ((input, expected) in cases) {
                (solution.javaClass.simpleName + " handles $input") {
                    solution.sumOfEvenFib(input) shouldBe expected
                }
            }
        }
    }
})
