package problem0001

import problem0001.solution1.P1S1
import problem0001.solution2.P1S2

/*
Problem statement from https://projecteuler.net/problem=1

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.
 */

interface P1Solution {
    fun sumMultiplesOf3And5(end: Int): Int
}

val solutions = listOf(P1S1(), P1S2())