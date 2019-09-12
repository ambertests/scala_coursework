package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 //expression
  println(x)

  println(2 + 3 * 4) // 14

  println(1 == x)  //false

  println(!(1 == x)) //true

  var aVariable = 2 // <-- note this is a var, not a val
  aVariable += 3
  println(aVariable) //5

  // Instructions vs Expressions
  // Instructions tell the computer what to DO
  // Expressions have a VALUE

  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)  // 5 because aCondition is true
  println(if(aCondition) 5 else 3) // also 5

  // LOOPS (generally discouraged in Scala)
  var i = 0
  while(i < 10){
    println(i)
    i += 1
  }
  // Loops are imperative programming, which is not the Scala way.

  // Everything in Scala is an Expression

  val aWeirdValue = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // side effects: println(), whiles, reassignment - all return Unit

  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

  println(aCodeBlock) // hello

  // 1. what is the difference between "hello world" and println("hello world")
  // --> "hello world" is a String value,
  //     println("hello world") is imperative statement with a value of Unit (void) with a side effect
  // 2. what is the value of this:
  val someValue = {
    2 < 3
  }
  // --> Boolean true, because 2 is less than 3
  println(someValue)
  // 3. what is the value of this:
  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  // --> 42, because that is the last expression
  println(someOtherValue)


}
