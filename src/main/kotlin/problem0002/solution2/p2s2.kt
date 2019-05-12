package problem0002.solution2

import problem0002.P2Solution

// The site (https://projecteuler.net/overview=002) points out that only each
// third number in the sequence is even. This code generates the whole sequence
// but only adds each third number to the sum, rather than using modulo to
// check for evenness.

class P2S2 : P2Solution {
    override fun sumOfEvenFib(upTo: Int): Int {
        var prev = 1
        var cur = 2
        var evenSum = 0
        var skip = 0
        while (cur <= upTo) {
            if (skip == 0) {
                evenSum += cur
                skip = 2
            } else {
                skip--
            }
            val next = cur + prev
            prev = cur
            cur = next
        }
        return evenSum
    }
}