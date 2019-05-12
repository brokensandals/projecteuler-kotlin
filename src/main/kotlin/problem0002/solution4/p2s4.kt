package problem0002.solution4

import problem0002.P2Solution
import java.lang.Math.round

// RudyPentato in the discussion thread (https://projecteuler.net/thread=2#210)
// points out that you can use the golden ratio (called phi, problem0002.solution4.Φ) to calculate
// each term in the Fibonacci sequence from the preceding term, rather than
// keeping track of the last two terms.
// The sequence of even Fibonacci numbers is just every third term in the
// Fibonacci sequence, so we can use problem0002.solution4.Φ^3 to skip straight to the even terms.
// Presumably this approach would eventually have rounding errors if you
// went far enough into the sequence, but it works for 4000000.

const val Φ = 1.6180339887 // No, I wouldn't use non-ASCII identifiers in real-word code, don't @ me
const val Φ_CUBED = Φ * Φ * Φ

class P2S4 : P2Solution {
    override fun sumOfEvenFib(upTo: Int): Int {
        var cur = 2
        var sum = 0
        while (cur <= upTo) {
            sum += cur
            cur = round(cur * Φ_CUBED).toInt()
        }
        return sum
    }
}