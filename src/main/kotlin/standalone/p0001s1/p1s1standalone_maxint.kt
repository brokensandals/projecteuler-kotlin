package standalone.p0001s1

fun main() {
    var sum = 0L

    for (i in 3 until Int.MAX_VALUE) {
        if (i % 3 == 0 || i % 5 == 0) {
            sum += i
        }
    }

    println(sum)
}