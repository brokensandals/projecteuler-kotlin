package problem0002.solution3

import problem0002.P2Solution

// The site (https://projecteuler.net/overview=002) points out that you can
// calculate the sequence of even Fibonacci numbers without calculating the
// intervening odd numbers. E(n) = E(n-2) + 4*E(n-1)

class P2S3 : P2Solution {
    override fun sumOfEvenFib(upTo: Int): Int {
        var prev = 2
        var cur = 8
        var sum = 2
        while (cur <= upTo) {
            sum += cur
            val next = prev + 4 * cur
            prev = cur
            cur = next
        }
        return sum
    }
}