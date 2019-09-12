package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3)) // hello 3

  def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction) // don't need parens to call function with no parameters

  def aRepeatedFunction(aString: String, n: Int): String = {
    if(n == 1) aString
    else aString + aRepeatedFunction(aString, n-1) // <-- recursion instead of loops!!!
  }

  println(aRepeatedFunction("hello", 3))

  def aFunctionWithSideEffects(aString: String): Unit = {
    println(aString)
  }

  aFunctionWithSideEffects("blah")

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int) = a + b

    aSmallerFunction(n, n -1)
  }

  println(aBigFunction(10))

  /*
  1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"
  2. Factorial function (n) => 1 * 2 * 3 ... * n
  3. Fibonacci function (n) => f(1) = 1, f(2) = 1, f(n) = f(n-1) + f(n-2)
  4. Tests if number is prime
   */

  def greetingFunction(name: String, age: Int): String = {
    "Hi, my name is " + name + " and I am " + age + " years old"
  }
  println(greetingFunction("Amber", 49))

  def factorialFunction(n: Int) : Int = {
    if(n <= 1) 1
    else n * (factorialFunction(n-1))
  }
  println(factorialFunction(4)) // 24

  def fibonacciFunction(n: Int): Int = {
    if (n <= 2) 1
    else fibonacciFunction(n-1) + fibonacciFunction(n-2)
  }
  println(fibonacciFunction(7)) // 13

  def isPrime(n: Int): Boolean = {
    @scala.annotation.tailrec
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1)  true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n/2)
  }

  println(isPrime(13)) //true
  println(isPrime(14)) //false
}
