package standalone.p0001s1

fun main() {
    var sum = 0

    for (i in 3 until 1000) {
        if (i % 3 == 0 || i % 5 == 0) {
            sum += i
        }
    }

    println(sum)
}