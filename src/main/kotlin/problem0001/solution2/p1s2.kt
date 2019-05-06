package problem0001.solution2

/*
Problem statement from https://projecteuler.net/problem=1

If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.

Find the sum of all the multiples of 3 or 5 below 1000.
 */

// My initial solution tried build up the sum incrementally while avoiding
// duplicates. The site (https://projecteuler.net/overview=001) pointed out
// that since those "duplicates" are the multiples 15, it's simpler to
// calculate the sums of the multiples of 3 and 5 and then to subtract the sum
// of the multiples of 15.

fun sumMultiples(of: Int, below: Int): Int {
    var sum = 0
    for (i in 0 until below step of) {
        sum += i
    }
    return sum
}

fun sumMultiplesOf3And5(end: Int): Int =
    sumMultiples(3, end) + sumMultiples(5, end) - sumMultiples(15, end)