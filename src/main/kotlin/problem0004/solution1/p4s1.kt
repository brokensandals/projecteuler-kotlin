package problem0004.solution1

/*
Problem statement from https://projecteuler.net/problem=4

A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
 */

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

fun largestPalindromeProduct(digits: Int): Long {
    val min = Math.pow(10.0, (digits - 1).toDouble()).toLong()
    val range = min until min * 10
    return range.flatMap { n1 -> range.map { n2 -> n1 * n2 } }
        .filter { isPalindrome(it) }
        .max() ?: throw IllegalArgumentException("No palindrome found")
}
