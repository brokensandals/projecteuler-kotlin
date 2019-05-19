package standalone.p0001s5

val end = 1000000000000L

fun sumOfMultiples(num: Long): Long {
    val terms = (end - 1) / num
    return num * terms * (terms + 1) / 2
}

fun main() {
    println(sumOfMultiples(3) + sumOfMultiples(5) - sumOfMultiples(15))
}