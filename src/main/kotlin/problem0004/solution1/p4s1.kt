package problem0004.solution1

import problem0004.P4Solution

// This is a simple, inefficient solution that checks whether every product
// in the range is a palindrome, and returns the largest one that is.

// To check for palindromes, it just converts the number to a string
// and compares the left half with the reversed right half.

fun isPalindrome(num: Long): Boolean {
    val str = num.toString()
    val left = str.substring(0, str.length / 2 + str.length % 2)
    val right = str.substring(str.length / 2)
    return left == right.reversed()
}

class P4S1 : P4Solution {
    override fun largestPalindromeProduct(digits: Int): Long {
        val min = Math.pow(10.0, (digits - 1).toDouble()).toLong()
        val range = min until min * 10
        return range.flatMap { n1 -> range.map { n2 -> n1 * n2 } }
            .filter { isPalindrome(it) }
            .max() ?: throw IllegalArgumentException("No palindrome found")
    }
}