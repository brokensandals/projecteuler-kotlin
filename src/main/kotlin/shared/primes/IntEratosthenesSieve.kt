package shared.primes

import java.util.*
import kotlin.collections.ArrayList

class IntEratosthenesSieve(precalculateUpTo: Int) {
    private val compositeFlags = BitSet(precalculateUpTo)
    val knownPrimes = ArrayList<Int>()
    var calculatedUpTo: Int = 1
        private set

    init {
        precalculate(precalculateUpTo)
    }

    private fun compositeFlagIndex(num: Int) = num - 2

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
                    compositeFlags[index] = true
                }
                knownPrimes.add(candidate)
            }
        }

        calculatedUpTo = upTo
    }

    fun isPrime(num: Int): Boolean? {
        if (num < 2) {
            throw IllegalArgumentException("Cannot test primality of numbers less than 2, got: $num")
        }

        if (num > calculatedUpTo) {
            return null
        }

        return !compositeFlags[compositeFlagIndex(num)]
    }

    fun isPrime(num: Long): Boolean? =
        if (num <= Int.MAX_VALUE) isPrime(num.toInt()) else null
}