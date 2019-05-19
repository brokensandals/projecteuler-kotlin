package standalone.p0001s1

val end = 1000L

fun sumOfMultiples(num: Long) = (num until end step num).sum()

fun main() {
    println(sumOfMultiples(3) + sumOfMultiples(5) - sumOfMultiples(15))
}