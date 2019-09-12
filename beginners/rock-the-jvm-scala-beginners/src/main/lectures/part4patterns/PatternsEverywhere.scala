package lectures.part4patterns

object PatternsEverywhere extends App {

  // big idea #1
  try{
    //code
  }catch{
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "null pointer"
    case _ => "something else"
  }
  //catches are actually matches

  // big idea #2
  val list = List(1,2,3,4)
  val evens = for{
    x <- list if x % 2 == 0
  }yield 10*x
  // generators based on pattern matching

  val tuples = List((1,2), (3,4))
  val filterTuples = for{
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, etc

  // big idea #3
  val tuple = (1,2,3)
  val(a,b,c) = tuple //assignment to multiple values at once
  println(b) //2

  val head :: tail = list
  println(head) // 1
  println(tail) // (2,3,4)

  // big idea #4
  // partial function
  val mappedList = list.map{
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the ONE"
    case _ => "something else"
  }
  //equivalent to...
  val mappedList2 = list.map(x => x match {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the ONE"
    case _ => "something else"
  })
  println(mappedList)
}
