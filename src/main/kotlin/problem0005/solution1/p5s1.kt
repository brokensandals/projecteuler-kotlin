package problem0005.solution1

import problem0005.P5Solution
import shared.primes.FactorizingIntEratosthenesSieve

// This solution uses the Sieve of Eratosthenes to find the prime
// factorizations of all the numbers in the range. Then it finds
// the least common multiple by multiplying together all the
// highest powers of those factors, as described at
// https://en.wikipedia.org/wiki/Least_common_multiple#Using_prime_factorization

class P5S1 : P5Solution {
    override fun leastCommonMultiple(range: IntRange): Long {
        val sieve = FactorizingIntEratosthenesSieve().precalculate(range.max() ?:
                throw IllegalArgumentException("Empty range"))

        val primeFactors = mutableMapOf<Long, Int>()

        for (factor in range) {
            if (factor == 1) {
                continue
            }

            for ((primeFactor, count) in sieve.primeFactors(factor)) {
                primeFactors.compute(primeFactor) { _, v -> maxOf(v ?: 0, count) }
            }
        }

        var product: Long = 1
        for ((factor, count) in primeFactors) {
            for (i in 1..count) {
                product *= factor
            }
        }

        return product
    }
}