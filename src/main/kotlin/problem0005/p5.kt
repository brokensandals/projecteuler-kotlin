package problem0005

import problem0005.solution1.P5S1

/*
Problem statement from https://projecteuler.net/problem=5

2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

interface P5Solution {
    fun leastCommonMultiple(range: IntRange): Long
}

val solutions = listOf(P5S1())