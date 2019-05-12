package problem0002.solution1

import problem0002.P2Solution

// This calculates each number in the Fibonacci sequence, checks whether it is
// even, and if so, adds it to the sum.

class P2S1 : P2Solution {
    override fun sumOfEvenFib(upTo: Int): Int {
        var prev = 1
        var cur = 2
        var evenSum = 0
        while (cur <= upTo) {
            if (cur % 2 == 0) {
                evenSum += cur
            }
            val next = cur + prev
            prev = cur
            cur = next
        }
        return evenSum
    }
}