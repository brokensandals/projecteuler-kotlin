package standalone.p0002s3

fun main() {
    var prev = 1
    var cur = 2
    var sum = 0

    while (cur <= 4000000) {
        sum += cur

        val next1 = cur + prev
        val next2 = cur + next1
        val next3 = next1 + next2
        prev = next2
        cur = next3
    }

    println(sum)
}