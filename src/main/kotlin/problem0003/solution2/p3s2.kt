package problem0003.solution2

import problem0003.P3Solution
import shared.primes.IntEratosthenesSieve
import java.util.*

// This is a recursive solution. For a given number, we find its first prime
// factor, divide by that and recurse to find the quotient's largest prime
// factor. The largest prime factor of the original number is whichever of
// those two (its first prime factor, or the largest prime factor of the
// quotient) is largest.

// This works since there is only one prime factorization of any given number.
// So when you find one prime factor, and divide by that, the prime factors
// of the resulting quotient are all the remaining prime factors of the
// original number.

// We use the Sieve of Eratosthenes to precalculate a list of all the prime
// numbers we'll need. Then when we need to find a number's first prime factor,
// we just start at the beginning of the list of primes and work our way
// through it until we find one the number is evenly divisible by.
//
// We only need a list of primes up to the square root of the
// original number, because:
// - a number can't have more than one prime factor greater than its square
//   root, since otherwise their product would be greater than the number
// - if a non-prime number has a prime factor greater than its square root,
//   it must have at least one prime factor less than its square root, to
//   let the product reach the number
// So, if none of the prime numbers up to the square root of a given number
// are factors of it, the number is prime.

class P3S2 : P3Solution {
    override fun largestPrimeFactor(number: Long): Long {
        val sieve = IntEratosthenesSieve().precalculate(
            Math.ceil(Math.sqrt(number.toDouble())).toInt()
        )

        fun firstPrimeFactor(number: Long): Long {
            if (sieve.isKnown(number) && sieve.isPrime(number)) {
                return number
            }

            return sieve.knownPrimes.takeWhile { it * it <= number }
                .filter { number % it == 0L }
                .firstOrNull()?.toLong() ?: number
        }

        fun recurse(number: Long): Long {
            val first = firstPrimeFactor(number)
            return if (first == number) {
                first
            } else {
                maxOf(first, recurse(number / first))
            }
        }

        return recurse(number)
    }
}