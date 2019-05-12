package problem0003

import problem0003.solution1.P3S1
import problem0003.solution2.P3S2

/*
Problem statement from https://projecteuler.net/problem=3

The prime factors of 13195 are 5, 7, 13 and 29.

What is the largest prime factor of the number 600851475143 ?
 */

interface P3Solution {
    fun largestPrimeFactor(number: Long): Long
}

val solutions = listOf(P3S1(), P3S2())