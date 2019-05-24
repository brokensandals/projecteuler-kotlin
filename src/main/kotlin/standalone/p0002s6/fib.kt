package standalone.p0002s6

fun main() {
    var prev = 2L
    var cur = 8L
    var count = 2

    while (cur <= Long.MAX_VALUE && cur > prev) {
        val next = prev + 4 * cur
        prev = cur
        cur = next
        count++
    }

    println(prev)
    println(count)
}