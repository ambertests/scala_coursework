package lectures.part3fp

object HOFsAndCurries extends App {
  //higher order function
  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  //return function is (Int => Int) function
  //parameters are Int, and a function which takes a string and returns a function
  //which takes an int and returns a boolean

  //function that applies a function n times over a value x
  //nTimes(f, n, x)
  //nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))

  @scala.annotation.tailrec
  def nTimes(f: Int => Int, n: Int, x:Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }

  val plus1 = (x:Int) => x + 1
  println(nTimes(plus1, 10, 1)) //11


  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if(n <= 0) (x: Int) => x
    else (x:Int) => nTimesBetter(f, n-1)(f(x))
  }

  val plus10 = nTimesBetter(plus1, 10)
  println(plus10(1)) //11

  //curried functions
  val superAdder = (x:Int) => (y:Int) => x + y
  val add3 = superAdder(3)
  print(add3(10)) //13
  println(superAdder(3)(10))

  // function defined with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val defaultFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(defaultFormat(Math.PI)) // 3.14
  println(preciseFormat(Math.PI)) // 3.14159265
}
