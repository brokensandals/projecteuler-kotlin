package standalone.p0001s1

fun main() {
    val end = 1000
    var mul3 = 3
    var mul5 = 5
    var sum = mul3 + mul5

    while (mul3 < end || mul5 < end) {
        if (mul3 < mul5) {
            mul3 += 3
            if (mul3 < end && mul3 != mul5) {
                sum += mul3
            }
            mul5 += 5
            if (mul5 < end && mul3 != mul5) {
                sum += mul5
            }
        }
    }

    println(sum)
}