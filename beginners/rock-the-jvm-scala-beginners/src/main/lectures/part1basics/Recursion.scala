package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int) : Int = {
    if(n <= 1) 1
    else {
      println("computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * (factorial(n-1))
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))
  //println(factorial(5000)) // stack overflow

  def anotherFactorial(n: Int): BigInt = { // has to be BigInt for larger values
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
      if(x <= 1) accumulator
      else factorialHelper(x-1, x*accumulator)
      // TAIL RECURSION - when the recursive call is the last expression
      // doesn't require intermediate results, so it doesn't overflow the stack
    }
    factorialHelper(n, 1)
  }

  println(anotherFactorial(5000)) // this works

  // When you need loops, use tail recursion

  /*
  1. Concatenate a string n times using tail recursion
  2. IsPrime function with tail recursion
  3. Tail recursive Fibonacci function
   */

  @tailrec
  def concat(s:String, n:Int, c:String): String = {
    if(n == 0) c
    else concat(s, n-1, c+s)
  }
  println(concat("a", 23, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int, isStillPrime: Boolean): Boolean = {
      if(!isStillPrime) false
      else if (t <= 1)  true
      else isPrimeUntil(t - 1, n % t != 0 && isStillPrime)
    }
    isPrimeUntil(n/2, true)
  }
  println(isPrime(9999))

  println(isPrime(8423))


  def fibonacci(n: Int): Int = {
    @tailrec
    def fibo(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fibo(i+1, last + nextToLast, last)
    }
    if(n <= 2) 1
    else fibo(2, 1, 1)
  }
  println(fibonacci(8))

}
