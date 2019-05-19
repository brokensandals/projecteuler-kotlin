package standalone.p0002s4

fun main() {
    var prev = 2
    var cur = 8
    var sum = 2

    while (cur <= 4000000) {
        sum += cur
        val next = prev + 4 * cur
        prev = cur
        cur = next
    }

    println(sum)
}