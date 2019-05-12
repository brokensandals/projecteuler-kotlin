package problem0004

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

val cases = mapOf(
    1 to 9L,
    2 to 9009L,
    3 to 906609L,
    4 to 99000099L
)

class P4Test : FreeSpec({
    for (solution in solutions) {
        solution.javaClass.name - {
            for ((input, expected) in cases) {
                (solution.javaClass.simpleName + " handles $input") {
                    solution.largestPalindromeProduct(input) shouldBe expected
                }
            }
        }
    }
})
