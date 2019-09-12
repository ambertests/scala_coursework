package lectures.part4patterns

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  (1 to 10).foreach( n => {
    val random = new Random
    val x = random.nextInt(10)
    val description = x match {
      case 1 => "the ONE"
      case 2 => "double or nothing"
      case 3 => "third time is the charm"
      case _ => "something else" // no default wildcard, then risk of a MatchError
    }
    println(x, description)
  })

  // 1. decompose value
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 25)
  val mary = Person("Mary", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am a minor"
    case Person(n, a) => s"My name is $n and I like beer"
    case _ => "Who am I?"
  }
  println(greeting)

  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends  Animal

  val animal:Animal = Parrot("schnauzer")
  animal match{
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    case Parrot(g) => println(s"The parrot says '$g'")
  }
  /*
  Exercises
  simple function uses pattern matching
  takes an Expr and makes readable form

  Sum(Number(2), Number(3)) => 2 + 3
  Product(Number(2), Number(3)) => 2 * 3
  Product(Sum(Number(2), Number(3)), Number(4)) => (2 + 3) * 4
  Sum(Product(Number(2), Number3), Number(4)) => 2 * 3 + 4
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Product(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = {
    e match {
      case Number(i) => i.toString
      case Sum(a, b) => show(a) + " + " + show(b)
      case Product(a, b) => {
        def maybeShowParens(exp: Expr): String = exp match {
          case Product(_,_) => show(exp)
          case Number(_) => show(exp)
          case _ => "(" + show(exp) + ")"
        }
        maybeShowParens(a) + " * " + maybeShowParens(b)
      }
    }
  }

  println(show(Sum(Number(2), Number(3)))) // => 2 + 3f
  println(show(Product(Number(2), Number(3)))) // => 2 * 3
  println(show(Product(Sum(Number(2), Number(3)), Number(4)))) // => (2 + 3) * 4
  println(show(Sum(Product(Number(2), Number(3)), Number(4)))) // => 2 * 3 + 4

}
