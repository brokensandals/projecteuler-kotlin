package shared.primes

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.FreeSpec
import kotlin.system.measureTimeMillis

class IntEratostenesSieveTest : FreeSpec({
    "when constructed without precalculateUpTo" - {
        "knows that 2 is prime" {
            IntEratosthenesSieve().isPrime(2L) shouldBe true
        }

        "does not know that 3 is prime" {
            IntEratosthenesSieve().isPrime(3L) shouldBe null
        }

        "can grow" {
            val sieve = IntEratosthenesSieve()
            sieve.precalculate(3)
            sieve.isPrime(3L) shouldBe true
        }
    }

    "when constructed with precalculateUpTo" - {
        "knows the highest prime in the given range" {
            IntEratosthenesSieve(1000).isPrime(997L) shouldBe true
        }

        "knows the highest composite in the given range" {
            IntEratosthenesSieve(1000).isPrime(1000L) shouldBe false
        }

        "does not know higher numbers" {
            IntEratosthenesSieve(1000).isPrime(1001L) shouldBe null
        }

        "can grow" {
            val sieve = IntEratosthenesSieve(1000)
            sieve.precalculate(2000)
            sieve.isPrime(1001L) shouldBe false
        }
    }

    "precalculate()" - {
        "does not allow values less than 2" {
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
            val sieve = IntEratosthenesSieve(2)
            sieve.precalculate(2)
            sieve.isPrime(2L) shouldBe true
            sieve.isPrime(3L) shouldBe null
            sieve.knownPrimes.size shouldBe 1
        }

        "allows the sieve to grow accurately" {
            val sieve = IntEratosthenesSieve(2)
            sieve.precalculate(1000)
            sieve.isPrime(997L) shouldBe true
            sieve.isPrime(1000L) shouldBe false
            sieve.isPrime(1500L) shouldBe null
            sieve.isPrime(1999L) shouldBe null
            sieve.isPrime(2001L) shouldBe null
            sieve.knownPrimes.size shouldBe 168

            sieve.precalculate(2000)
            sieve.isPrime(997L) shouldBe true
            sieve.isPrime(1000L) shouldBe false
            sieve.isPrime(1500L) shouldBe false
            sieve.isPrime(1999L) shouldBe true
            sieve.isPrime(2001L) shouldBe null
            sieve.knownPrimes.size shouldBe 303
        }

        "handles Int.MAX_VALUE" {
            val sieve = IntEratosthenesSieve(2)
            val millis = measureTimeMillis {
                sieve.precalculate(Int.MAX_VALUE)
            }
            println("Time to precalculate all primes up to " +
                "Int.MAX_VALUE by Sieve of Eratosthenes: $millis milliseconds")
            sieve.isPrime(2147483646) shouldBe false
            sieve.isPrime(2147483647) shouldBe true
            sieve.knownPrimes.size shouldBe 105097565 // thanks, Wolfram Alpha
        }
    }
})