package problem0004.solution2

/*
Problem statement from https://projecteuler.net/problem=4

A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
 */

// This solution generates a series of products of numbers of the given length,
// and tests whether each is a palindrome. It includes some optimizations to
// skip generation of some products when they can't possibly be larger than
// the best palindrome we've already found.
//
// To test whether a number is a palindrome, we build repeatedly divide it by
// 10. After each division, we add the remained to another variable, which
// is multiplied by 10 before the next round. At the end, that variable
// will contain the reverse of the original number; iff it and the original
// are equal, we have a palindrome.
//
// To generate the series of products, we count down from the highest N-digit
// number in an outer loop, and then count down from that number in an inner
// loop. This means that within one iteration of the outer loop, each iteration
// of the inner loop will generate a progressively lower product. So, as soon
// as we find any palindrome, or find any product that is lower than the
// largest palindrome we've found, we can skip to the next iteration of the
// outer loop.

fun isPalindrome(num: Long): Boolean {
    var reversed: Long = 0
    var quotient: Long = num
    while (quotient > 0) {
        reversed = reversed * 10 + (quotient % 10)
        quotient = quotient / 10
    }
    return reversed == num
}

fun largestPalindromeProduct(digits: Int): Long {
    val min = Math.pow(10.0, (digits - 1).toDouble()).toLong()
    var best: Long = -1

    outer@ for (factor1 in (min * 10 - 1) downTo min) {
        for (factor2 in factor1 downTo min) {
            val product = factor1 * factor2
            if (product < best) {
                continue@outer
            }

            if (isPalindrome(product)) {
                if (product > best) {
                    best = product
                }
                continue@outer
            }
        }
    }

    if (best < 0) {
        throw IllegalArgumentException("No palindrome found")
    }

    return best
}
