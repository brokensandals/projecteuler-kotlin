package problem0003.solution1

/*
Problem statement from https://projecteuler.net/problem=3

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
 */

// This solution uses a simple (inefficient) way of lazily generating
// a list of prime numbers, and a recursive prime factorization algorithm.

/**
 * Determines if the given number is prime by checking that it is not
 * evenly divisible by any number greater than 2 or less than itself.
 */
fun isPrime(x: Long): Boolean {
    for (i in 2 until x) {
        if (x % i == 0L) {
            return false
        }
    }
    return true
}

/**
 * A lazy sequence of all primes.
 * Note that this is particularly inefficient because it does not cache
 * the values it calculates - every time you iterate from the beginning
 * of the sequence, it repeats all the calculations.
 */
val PRIMES = generateSequence(2L) { it + 1 }
    .filter { isPrime(it) }

/**
 * Returns a list of all the prime factors of the given number.
 * Works recursively: if the number itself is prime, we just return it.
 * Otherwise, we find the smallest prime number it's evenly divisible by,
 * and recurse to find all the other prime factors.
 */
fun primeFactors(x: Long): List<Long> {
    if (isPrime(x)) {
        return listOf(x)
    }

    val firstPrimeFactor = PRIMES.first { x % it == 0L }
    return listOf(firstPrimeFactor).plus(primeFactors(x / firstPrimeFactor))
}

fun largestPrimeFactor(n: Long) = primeFactors(n).max()