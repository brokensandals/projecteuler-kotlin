package shared.primes

import java.util.*
import kotlin.collections.ArrayList

/**
 * Calculates prime numbers using the Sieve of Eratosthenes
 * algorithm: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 *
 * Instances are NOT thread-safe.
 */
open class IntEratosthenesSieve() {
    protected val INDEX_OFFSET = -2

    private val compositeFlags = BitSet()

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

    open protected fun foundPrimeFactor(factor: Int, num: Int) {}

    /**
     * Determines the primality of all numbers less than or equal to
     * the given number. Updates knownPrimes and calculatedUpTo accordingly.
     * @return this instance
     */
    open fun precalculate(upTo: Int): IntEratosthenesSieve {
        if (upTo < 2) {
            throw IllegalArgumentException("upTo must be >= 2, got: $upTo")
        }

        if (upTo < calculatedUpTo) {
            return this
        }

        for (prime in knownPrimes) {
            val start = calculatedUpTo + prime - (calculatedUpTo % prime)
            for (composite in start..upTo step prime) {
                compositeFlags[composite + INDEX_OFFSET] = true
                foundPrimeFactor(prime, composite)
            }
        }

        for (candidate in (calculatedUpTo + 1)..upTo) {
            if (!compositeFlags[candidate + INDEX_OFFSET]) {
                foundPrimeFactor(candidate, candidate)
                for (composite in (candidate * 2)..upTo step candidate) {
                    val index = composite + INDEX_OFFSET
                    if (index < 0) {
                        // This will happen for primes > Int.MAX_INT/2
                        // Since we don't support checking primality of any
                        // numbers > Int.MAX_INT there's no need to mark
                        // those as composite.
                        break
                    }
                    compositeFlags[index] = true
                    foundPrimeFactor(candidate, composite)
                }
                knownPrimes.add(candidate)
            }
        }

        calculatedUpTo = upTo

        return this
    }

    /**
     * Returns true iff the sieve has calculated results for the given number.
     * @throws IllegalArgumentException for values less than 2
     */
    fun isKnown(num: Long): Boolean {
        if (num < 2) {
            throw IllegalArgumentException("Primality of $num is undefined")
        }

        if (num > calculatedUpTo) {
            return false
        }

        return true
    }

    fun isKnown(num: Int): Boolean = isKnown(num.toLong())

    /**
     * Returns true if the given number is prime, false if it is not.
     * @throws IllegalArgumentException if isKnown(num) is false
     */
    fun isPrime(num: Long): Boolean {
        if (!isKnown(num)) {
            throw IllegalArgumentException("Primality of $num is unknown")
        }

        // converting to int is safe because isKnown() would have returned false
        // if the number were out of range
        return !compositeFlags[num.toInt() + INDEX_OFFSET]
    }

    fun isPrime(num: Int): Boolean = isPrime(num.toLong())
}

/**
 * Extends the Eratosthenes sieve by also precalculating every number's
 * list of prime factors.
 */
class FactorizingIntEratosthenesSieve : IntEratosthenesSieve() {
    internal val primeFactors = mutableListOf<SortedMap<Long, Int>>()

    override fun precalculate(upTo: Int): FactorizingIntEratosthenesSieve {
        for (i in primeFactors.size until upTo) {
            primeFactors.add(sortedMapOf())
        }

        super.precalculate(upTo)
        return this
    }

    override fun foundPrimeFactor(factor: Int, num: Int) {
        if (factor == num) {
            primeFactors[num + INDEX_OFFSET].put(factor.toLong(), 1)
        } else {
            val times = primeFactors[(num / factor) + INDEX_OFFSET].getOrDefault(factor.toLong(), 0) + 1
            primeFactors[num + INDEX_OFFSET].put(factor.toLong(), times)
        }
    }

    /**
     * Returns the prime factors of the given number, in the form of a map
     * where the key is a prime factor and the value is the number of times
     * that factor occurs in the factorization. For example, primeFactors(12)
     * would return sortedMapOf(2 to 2, 3 to 1)
     *
     * @throws IllegalArgumentException if isKnown(num) is false
     */
    fun primeFactors(num: Long): SortedMap<Long, Int> {
        if (!isKnown(num)) {
            throw IllegalArgumentException("Prime factors of $num are unknown")
        }

        // converting to int is safe because isKnown() would have returned false
        // if the number were out of range
        return primeFactors[num.toInt() + INDEX_OFFSET]
    }

    fun primeFactors(num: Int): SortedMap<Long, Int> = primeFactors(num.toLong())
}