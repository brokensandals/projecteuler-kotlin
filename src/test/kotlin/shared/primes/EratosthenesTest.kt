package shared.primes

import io.kotlintest.matchers.maps.shouldContainExactly
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import kotlin.system.measureTimeMillis

class IntEratostenesSieveTest : FreeSpec({
    "isKnown" - {
        "knows the lowest prime in the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isKnown(2L) shouldBe true
        }

        "knows a prime in the middle of the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isKnown(17L) shouldBe true
        }

        "knows a composite in the middle of the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isKnown(502L) shouldBe true
        }

        "knows the highest prime in the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isKnown(997L) shouldBe true
        }

        "knows the highest composite in the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isKnown(1000L) shouldBe true
        }

        "does not know higher numbers than the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isKnown(1001L) shouldBe false
        }

        "does not allow less than 2" {
            val sieve = IntEratosthenesSieve().precalculate(1000)
            shouldThrow<IllegalArgumentException> {
                sieve.isKnown(1)
            }

            shouldThrow<IllegalArgumentException> {
                sieve.isKnown(0)
            }

            shouldThrow<IllegalArgumentException> {
                sieve.isKnown(1)
            }
        }
    }

    "isPrime" - {
        "knows the lowest prime in the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isPrime(2L) shouldBe true
        }

        "knows a prime in the middle of the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isPrime(17L) shouldBe true
        }

        "knows a composite in the middle of the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isPrime(502L) shouldBe false
        }

        "knows the highest prime in the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isPrime(997L) shouldBe true
        }

        "knows the highest composite in the precalculated range" {
            IntEratosthenesSieve().precalculate(1000).isPrime(1000L) shouldBe false
        }

        "does not allow higher numbers than the precalculated range" {
             shouldThrow<IllegalArgumentException> {
                 IntEratosthenesSieve().precalculate(1000).isPrime(1001L)
             }
        }

        "does not allow less than 2" {
            val sieve = IntEratosthenesSieve().precalculate(1000)
            shouldThrow<IllegalArgumentException> {
                sieve.isPrime(1)
            }

            shouldThrow<IllegalArgumentException> {
                sieve.isPrime(0)
            }

            shouldThrow<IllegalArgumentException> {
                sieve.isPrime(1)
            }
        }
    }

    "precalculate" - {
        "does not allow less than 2" {
            shouldThrow<IllegalArgumentException> {
                IntEratosthenesSieve().precalculate(1)
            }

            shouldThrow<IllegalArgumentException> {
                IntEratosthenesSieve().precalculate(0)
            }

            shouldThrow<IllegalArgumentException> {
                IntEratosthenesSieve().precalculate(-1)
            }
        }

        "allows 2" {
            val sieve = IntEratosthenesSieve().precalculate(2)
            sieve.isPrime(2L) shouldBe true
            sieve.isKnown(3L) shouldBe false
            sieve.knownPrimes.size shouldBe 1
        }

        "allows the sieve to grow accurately" {
            val sieve = IntEratosthenesSieve().precalculate(1000)
            sieve.isPrime(997L) shouldBe true
            sieve.isPrime(1000L) shouldBe false
            sieve.isKnown(1500L) shouldBe false
            sieve.isKnown(1999L) shouldBe false
            sieve.isKnown(2001L) shouldBe false
            sieve.knownPrimes.size shouldBe 168

            sieve.precalculate(2000)
            sieve.isPrime(997L) shouldBe true
            sieve.isPrime(1000L) shouldBe false
            sieve.isPrime(1500L) shouldBe false
            sieve.isPrime(1999L) shouldBe true
            sieve.isKnown(2001L) shouldBe false
            sieve.knownPrimes.size shouldBe 303
        }

        "allows Int.MAX_VALUE" {
            val sieve = IntEratosthenesSieve()
            val millis = measureTimeMillis {
                sieve.precalculate(Int.MAX_VALUE)
            }
            println(
                "Time to precalculate all primes up to " +
                        "Int.MAX_VALUE by Sieve of Eratosthenes: $millis milliseconds"
            )
            sieve.isPrime(2147483646) shouldBe false
            sieve.isPrime(2147483647) shouldBe true
            sieve.knownPrimes.size shouldBe 105097565 // thanks, Wolfram Alpha
        }
    }
})

class FactorizingIntEratosthenesSieveTest : FreeSpec({
    "primeFactors" - {
        "knows the lowest prime in the precalculated range" {
            FactorizingIntEratosthenesSieve().precalculate(1000)
                .primeFactors(2L) shouldContainExactly mapOf(2L to 1)
        }

        "knows a prime in the middle of the precalculated range" {
            FactorizingIntEratosthenesSieve().precalculate(1000)
                .primeFactors(17L) shouldContainExactly mapOf(17L to 1)
        }

        "knows a composite in the middle of the precalculated range" {
            FactorizingIntEratosthenesSieve().precalculate(1000)
                .primeFactors(502L) shouldContainExactly mapOf(2L to 1, 251L to 1)
        }

        "knows the highest prime in the precalculated range" {
            FactorizingIntEratosthenesSieve().precalculate(1000)
                .primeFactors(997L) shouldContainExactly mapOf(997L to 1)
        }

        "knows the highest composite in the precalculated range" {
            FactorizingIntEratosthenesSieve().precalculate(1000)
                .primeFactors(1000L) shouldContainExactly mapOf(2L to 3, 5L to 3)
        }

        "does not allow higher numbers than the precalculated range" {
            val sieve = FactorizingIntEratosthenesSieve().precalculate(1000)
            shouldThrow<IllegalArgumentException> { sieve.primeFactors(1001L) }
        }

        "does not allow less than 2" {
            val sieve = FactorizingIntEratosthenesSieve().precalculate(1000)
            shouldThrow<IllegalArgumentException> {
                sieve.primeFactors(1)
            }

            shouldThrow<IllegalArgumentException> {
                sieve.primeFactors(0)
            }

            shouldThrow<IllegalArgumentException> {
                sieve.primeFactors(1)
            }
        }
    }

    "precalculate" - {
        "allows the sieve to grow accurately" {
            val sieve = FactorizingIntEratosthenesSieve().precalculate(1000)
            sieve.primeFactors(1000L) shouldContainExactly mapOf(2L to 3, 5L to 3)

            sieve.precalculate(2000)
            sieve.primeFactors(1000L) shouldContainExactly mapOf(2L to 3, 5L to 3)
            sieve.primeFactors(1500L) shouldContainExactly mapOf(2L to 2, 3L to 1, 5L to 3)

            sieve.primeFactors.flatMap { it.values }.sum() shouldBe 5971 // thanks again, Wolfram Alpha
        }

        "allows a large value" {
            val upTo = 10000000
            val sieve = FactorizingIntEratosthenesSieve()
            val millis = measureTimeMillis {
                sieve.precalculate(upTo)
            }
            println(
                "Time to precalculate all prime factorizations up to " +
                        "$upTo by Sieve of Eratosthenes: $millis milliseconds"
            )

            // I haven't verified this test value independently, it's simply the
            // result the current code gives.
            sieve.primeFactors.flatMap { it.values }.sum() shouldBe 37861249
        }
    }
})