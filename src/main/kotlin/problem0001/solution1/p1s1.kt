package problem0001.solution1

import problem0001.P1Solution

// In this approach, I keep track of the current multiple of 3 and the current
// multiple of 5, and the current sum. Then I loop until all multiples have
// been added into the sum. Each iteration of the loop increments *either*
// the threes variable, *or* the fives variable, before adding it to the sum.
// To avoid adding the same number twice (when it's a multiple of both 3 and 5)
// the loop ensures that the threes variable always reaches any given number
// first, and when the threes and fives variables are equal the number is not
// added to the sum (since it will already have been added when the threes
// variable was updated).

class P1S1 : P1Solution {
    override fun sumMultiplesOf3And5(end: Int): Int {
        var threes = 3
        var fives = 5
        var sum = threes + fives

        while (threes < end || fives < end) {
            if (threes < fives) {
                threes += 3
                if (threes < end && threes != fives) {
                    sum += threes
                }
            } else {
                fives += 5
                if (fives < end && threes != fives) {
                    sum += fives
                }
            }
        }

        return sum
    }
}