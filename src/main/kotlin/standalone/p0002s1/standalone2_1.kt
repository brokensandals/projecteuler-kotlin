package standalone.p0002s1

fun main() {
    var prev = 1
    var cur = 2
    var sum = 0

    while (cur <= 4000000) {
        if (cur % 2 == 0) {
            sum += cur
        }
        val next = cur + prev
        prev = cur
        cur = next
    }

    println(sum)
}