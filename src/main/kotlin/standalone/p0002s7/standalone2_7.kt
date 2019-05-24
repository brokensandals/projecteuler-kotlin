package standalone.p0002s7

val SQRT_5 = Math.sqrt(5.0)
val Φ = (SQRT_5 + 1.0) / 2.0
val LN_Φ = Math.log(Φ)

fun fib(n: Int) = Math.round(Math.pow(Φ, n.toDouble()) / SQRT_5)

fun evenFib(n: Int) = fib(3 * n)

fun evenFibSum(n: Int) = (evenFib(n) + evenFib(n + 1) - 2) / 4

fun fibIndex(f: Long) = Math.floor(
    Math.log(f * SQRT_5 + 0.5) / LN_Φ).toInt()

fun evenFibIndex(f: Long) = fibIndex(f) / 3

fun main() {
    println(evenFibSum(evenFibIndex(4000000L - 1)))
}