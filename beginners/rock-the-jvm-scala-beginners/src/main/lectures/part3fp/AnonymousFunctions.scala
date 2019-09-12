package lectures.part3fp

object AnonymousFunctions extends App {
  // anonymous function (LAMBDA)
  val doubler = (x:Int) => x*2
  //instantiates new Function1 and overrides apply method
  // doubler is type of Int => Int
  val doubler2: Int => Int = x => x*2

  //multiple parameters
  val adder = (a: Int, b: Int) => a + b

  //no parameters
  val justDoSomething = () => 3

  println(justDoSomething) //exercises.part3fp.AnonymousFunctions$$$Lambda$5/932583850@6fffcba5
  println(justDoSomething()) //3
  // lambdas *must* be called with parenthesis

  // curly braces with lambda

  val stringToInt = { (str:String) =>
    str.toInt
  }

  // more syntactic sugar: underscore stands in for parameter
  val niceIncrementer: Int => Int = _+1 // equivalent to x => x+1
  val niceAdder: (Int, Int) => Int = _+_

  /*Exercises
  1) replace all functionx calls in MyList with lambdas
  2) Rewrite the curried adder as an anonymous function
   */

}
