package problem0002

import problem0002.solution1.P2S1
import problem0002.solution2.P2S2
import problem0002.solution3.P2S3
import problem0002.solution4.P2S4

/*
Problem statement from https://projecteuler.net/problem=2

Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:

1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
 */

interface P2Solution {
    fun sumOfEvenFib(upTo: Int): Int
}

val solutions = listOf(P2S1(), P2S2(), P2S3(), P2S4())