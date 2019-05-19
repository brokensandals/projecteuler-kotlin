package standalone.p0002s5

import java.lang.Math.round

const val Φ = 1.6180339887 // No, I wouldn't use non-ASCII identifiers in real-word code, don't @ me
const val Φ_CUBED = Φ * Φ * Φ

fun main() {
    var cur = 2
    var sum = 0

    while (cur <= 4000000) {
        sum += cur
        cur = round(cur * Φ_CUBED).toInt()
    }

    println(sum)
}