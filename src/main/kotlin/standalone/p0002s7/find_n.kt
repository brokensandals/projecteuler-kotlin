package standalone.p0002s7

fun main() {
    var prev = 0L
    var cur = 1L

    for (i in 1..100) {
        val index = fibIndex(cur)
        if (index != i) {
            println("FOR $cur GOT $index INSTEAD OF $i")
        } else {
            println("RIGHT: $cur is $i")
        }

        val next = prev + cur
        prev = cur
        cur = next
    }
}