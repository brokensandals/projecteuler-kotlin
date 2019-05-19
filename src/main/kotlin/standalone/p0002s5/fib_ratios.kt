package standalone.p0002s5

fun main() {
    var prev = 1
    var cur = 2

    println("|n|Fib(n)|Fib(n)/Fib(n-1)|")
    println("|---|---|---|")
    println("|1|1||")
    for (i in 2..10) {
        val quotient = cur.toDouble() / prev.toDouble()
        println("|$i|$cur|$quotient|")

        val next = cur + prev
        prev = cur
        cur = next
    }
}