package standalone.p0002s6

fun main() {
    var prev = 2L
    var cur = 8L

    while (cur <= 1000000000000000L) {
        val next = prev + 4 * cur
        prev = cur
        cur = next
    }

    val sum = (prev + cur - 2) / 4
    println(sum)
}