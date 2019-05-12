package problem0004

import problem0004.solution1.P4S1
import problem0004.solution2.P4S2

/*
Problem statement from https://projecteuler.net/problem=4

A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
 */

interface P4Solution {
    fun largestPalindromeProduct(digits: Int): Long
}

val solutions = listOf(P4S1(), P4S2())