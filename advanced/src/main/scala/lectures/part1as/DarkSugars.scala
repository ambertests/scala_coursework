package lectures.part1as

import scala.util.Try

object DarkSugars extends App {
  // syntax sugar #1: methods with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    // write some complex code to derive the arg
    42
  }
  println(description)

  val aTryInstance = Try {
    // makes the scala Try.apply method act more like Java try block
    throw new RuntimeException
  }

  List(1,2,3).map {
    x => x + 1
  }

  //syntax sugar #2: single abstract method

  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action{
    override def act(x: Int): Int = x + 1
  }
  // single abstract method can be implemented as a lambda
  val aFunkyInstance: Action = (x:Int) => x + 1

  //example: Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello")
  })
  val aSweeterThread = new Thread(() => println("hello, sweetie"))

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a:Int): Unit
  }
  val anAbstractInstance: AnAbstractType = (a: Int) => println("sweet")

  //syntax sugar #3: the :: and #:: methods are special
  val prependedList = 2 :: List(3,4)
  // scala spec: last char decides associativity of method
  // so instead of 2.::(List), which doesn't work,
  // the compiler does List(3,4).::(2)

  class MyStream[T]{
    def -->:(value: T): MyStream[T] = this
  }
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]

  // syntax sugar #4: multi-work method naming

  class TeenGirl(name: String){
    def `and then said`(gossip: String): Unit = println(s"$name said $gossip")
  }
  val becky = new TeenGirl("Becky")

  becky `and then said` "Oh. My. God. Look at her butt"

  //syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Int Composite String = ???

  class -->[A, B]
  val towards: Int --> String = ???

  //syntax sugar #6: update() method is special, like apply()
  val anArray = Array(1,2,3)
  anArray(2) = 7 // rewritten to anArray.update(2, 7) --> Array(1,2,7)
  //used in mutable collections
  //remember apply() and update()

  //syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0
    def member: Int = internalMember //getter
    def member_=(value: Int): Unit = {
      internalMember = value //setter
    }
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewritten as aMutableContainer.member_=(42)
}
