package problem0003.solution2

import shared.primes.IntEratosthenesSieve
import java.util.*

/*
Problem statement from https://projecteuler.net/problem=3

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
 */

const val TARGET = 600851475143L

/**
 * Finds all the prime numbers less than or equal to the given number, via the
 * Sieve of Eratosthenes: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 *
 * Returns the result as a SortedSet so that you can easily either iterate
 * over all the primes in order, or test whether a particular number is prime.
 */
fun primesByEratosthenes(max: Int): SortedSet<Int> {
    val isComposite = BitSet(max)
    val primes = TreeSet<Int>()
    var curCandidate = 2
    while (curCandidate <= max) {
        if (!isComposite[curCandidate - 1]) {
            val curPrime = curCandidate
            primes.add(curPrime)
            for (curComposite in (curPrime * 2)..max step curPrime) {
                isComposite[curComposite - 1] = true
            }
        }

        curCandidate++
    }
    return primes
}

val PRIMES = IntEratosthenesSieve(
    Math.ceil(Math.sqrt(TARGET.toDouble())).toInt())

fun firstPrimeFactor(number: Long): Long {
    if (PRIMES.isPrime(number) == true) {
        return number
    }

    return PRIMES.knownPrimes.takeWhile { it * it <= number }
        .filter { number % it == 0L }
        .firstOrNull()?.toLong() ?: number
}

fun largestPrimeFactor(number: Long): Long {
    val first = firstPrimeFactor(number)
    return if (first == number) {
        first
    } else {
        maxOf(first, largestPrimeFactor(number / first))
    }
}

fun main() {
    println(largestPrimeFactor(TARGET))
}