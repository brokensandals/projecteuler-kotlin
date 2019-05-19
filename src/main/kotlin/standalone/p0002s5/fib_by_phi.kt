package standalone.p0002s5


fun main() {
////  var curFib = 1
////    var fibEstimate = 1.0
////
////    for (i in 2..20) {
////        println("n=$i  Fib(n)=$curFib  estimate=$curFib")
////        curFib = Math.round(curFib.toDouble() * Φ).toInt()
////        fibEstimate *= Φ
////    }
////
////    var curEvenFib = 2
////    var evenFibEstimate = 2.0
////
////    println("\n\n\nEVENS")
////    for (i in 1..20) {
////        println("n=$i  EvenFib(n)=$curEvenFib  estimate=$evenFibEstimate")
////        curEvenFib = Math.round(curEvenFib.toDouble() * Φ_CUBED).toInt()
////        evenFibEstimate *= Φ_CUBED
////    }

    var curFibByEstimate = 1L
    var prevFibExact = 1L
    var curFibExact = 1L

    for (i in 2..Int.MAX_VALUE) {
        if (curFibByEstimate != curFibExact) {
            println("Diverged at i=$i: estimate=$curFibByEstimate, exact=$curFibExact")
            break
        }

        val nextExact = curFibExact + prevFibExact
        prevFibExact = curFibExact
        curFibExact = nextExact

        curFibByEstimate = Math.round(curFibByEstimate.toDouble() * Φ).toLong()
    }
}