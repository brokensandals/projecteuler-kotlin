package standalone.p0002s7

fun main() {
    var prev = 0L
    var cur = 1L

    for (i in 1..100) {
        val est = fib(i)
        if (cur != fib(i)) {
            println("FOR $i GOT $est INSTEAD OF $cur")
        }

        val next = prev + cur
        prev = cur
        cur = next
    }
}