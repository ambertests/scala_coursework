package lectures.part1as

import scala.annotation.tailrec

object Recap extends App {

  val aCondition: Boolean = false
  val aConditionedVal = if(aCondition) 42 else 65
  // instructions vs expressions:
  //    instructions are imperatives executed in sequence
  //    expressions are used more for functional programming

  val aCodeBlock = {
    if(aCondition) 54
    56
  }

  // Unit: Scala-specific "void" return
  val theUnit = println("hello scala")

  // functions
  def aFunction(x:Int): Int = x + 1

  // recursion: stack and tail
  @tailrec def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n - 1, n * accumulator)
  // tail recursion prevents stack overflow

  // object oriented programming
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog // subtyping polymorphism

  trait Carnivore{
    def eat(a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore{
    override def eat(a: Animal): Unit = println("croc-crunch")
  }

  //method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog //natural language

  //operators are actually methods
  1 + 2
  1.+(2)

  //anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("slurp")
  }

  //generics
  //covariant list
  abstract class MyList[+A]
  //variance and variance problems to be covered in this course

  //singletons and companions
  object MyList

  //case classes
  case class Person(name: String, age: Int) //comes with lots of stuff
  val aPerson = Person("Joe", 13)

  //exceptions
  val throwsException = throw new RuntimeException //Nothing
  val aPotentialFailure = try{
    throw new RuntimeException
  } catch{
    case e: Exception => "caught an exception"
  }finally {
    println("some logs")
  }

  //packaging and imports: not covered so much in this course

  // functional programming
  // functions are instances of objects with apply methods

  val incrementor = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }

  incrementor(1) //2

  val anonymousIncrementer = (x: Int) => x + 1
  List(1,2,3).map(anonymousIncrementer) //List(2,3,4)
  //map, flatMap, filter

  //for-comprehension
  val pairs = for{
    num <- List(1,2,3)
    char <- List('a', 'b', 'c')
  } yield num + "-" + char

  // Scala collections: Seqs, Arrays, Lists, Vectors, Maps, Tuples
  val aMap = Map(
    "Daniel" -> 789,
    "Jess" -> 555
  )

  //"collections": Options, Try
  val anOption = Some(2)

  // pattern matching
  val x = 2
  val order = x match{
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => x + "th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hello, I'm $n"
  }




}
