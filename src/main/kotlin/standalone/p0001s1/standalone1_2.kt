package standalone.p0001s1

fun main() {
    println((3 until 1000)
        .filter { it % 3 == 0 || it % 5 == 0 }
        .sum())
}