package standalone.p0002s2

fun main() {
    var prev = 1
    var cur = 2
    var sum = 0
    var skip = 0

    while (cur <= 4000000) {
        if (skip == 0) {
            sum += cur
            skip = 2
        } else {
            skip--
        }
        val next = cur + prev
        prev = cur
        cur = next
    }

    println(sum)
}