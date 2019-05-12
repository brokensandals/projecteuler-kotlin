package problem0001.solution2

import problem0001.P1Solution

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

class P1S2 : P1Solution {
    override fun sumMultiplesOf3And5(end: Int): Int =
        sumMultiples(3, end) + sumMultiples(5, end) - sumMultiples(15, end)
}