package shared.primes

import java.util.*
import kotlin.collections.ArrayList

/**
 * Calculates prime numbers using the Sieve of Eratosthenes
 * algorithm: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 *
 * Instances are NOT thread-safe.
 */
class IntEratosthenesSieve(precalculateUpTo: Int = 2) {
    private val compositeFlags = BitSet(precalculateUpTo)

    /**
     * The list of primes found so far. This will be in sorted order
     * and will contain all the primes less than or equal to
     * calculatedUpTo.
     */
    val knownPrimes = ArrayList<Int>()

    /**
     * The highest number (prime or composite) that this instance has
     * determined the primality of.
     */
    var calculatedUpTo: Int = 1
        private set

    init {
        precalculate(precalculateUpTo)
    }

    private fun compositeFlagIndex(num: Int) = num - 2

    /**
     * Determines the primality of all numbers less than or equal to
     * the given number. Updates knownPrimes and calculatedUpTo accordingly.
     */
    fun precalculate(upTo: Int) {
        if (upTo < 2) {
            throw IllegalArgumentException("upTo must be >= 2, got: $upTo")
        }

        if (upTo < calculatedUpTo) {
            return
        }

        for (prime in knownPrimes) {
            val start = compositeFlagIndex(calculatedUpTo + prime - (calculatedUpTo % prime))
            for (index in start..compositeFlagIndex(upTo) step prime) {
                compositeFlags[index] = true
            }
        }

        for (candidate in (calculatedUpTo + 1)..upTo) {
            if (!compositeFlags[compositeFlagIndex(candidate)]) {
                for (index in compositeFlagIndex(candidate * 2)..compositeFlagIndex(upTo) step candidate) {
                    if (index < 0) {
                        // This will happen for primes > Int.MAX_INT/2
                        // Since we don't support checking primality of any
                        // numbers > Int.MAX_INT there's no need to mark
                        // those as composite.
                        break
                    }
                    compositeFlags[index] = true
                }
                knownPrimes.add(candidate)
            }
        }

        calculatedUpTo = upTo
    }

    /**
     * Returns true if the given number is prime, false if it is not,
     * and null if unknown. The result will be null iff the number is
     * greater than calculatedUpTo.
     */
    fun isPrime(num: Int): Boolean? {
        if (num < 2) {
            throw IllegalArgumentException("Cannot test primality of numbers less than 2, got: $num")
        }

        if (num > calculatedUpTo) {
            return null
        }

        return !compositeFlags[compositeFlagIndex(num)]
    }

    /**
     * Returns true if the given number is prime, false if it is not,
     * and null if unknown. The result will be null iff the number is
     * greater than calculatedUpTo.
     */
    fun isPrime(num: Long): Boolean? =
        if (num <= Int.MAX_VALUE) isPrime(num.toInt()) else null
}