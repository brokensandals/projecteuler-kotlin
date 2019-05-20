package standalone.p0002s6

fun main() {
    var prev = 2
    var cur = 8

    while (cur <= 4000000) {
        val next = prev + 4 * cur
        prev = cur
        cur = next
    }

    val sum = (prev + cur - 2) / 4
    println(sum)
}