package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  /*
  trait Seq[+A]
  def head: A
  def tail: Seq[A]
   */
  val aSequence =Seq(1,3,2,4)
  // Seq[Int] implemented as a List
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  val aRange: Seq[Int] = 0 to 9
  aRange.foreach(print)
  println

  (1 to 10).foreach(x => println("Hi " + x))

  //lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended)
  val appended = aList :+ 42
  println(appended)

  val apples5 = List.fill(5)("apple") //curried function
  println(apples5)
  println(aList.mkString("-")) //like python join

  //arrays - constructed with pre-defined length and *mutable*

  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  println(numbers) //[I@4c203ea1 Java native array object
  println(threeElements.foreach(println))
  numbers(2) = 0
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq) //ArraySeq(1, 2, 0, 4) implicit conversion

  // vectors

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  //vectors vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for{
      iter <- 1 to maxRuns
    }yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    (times.sum * 1.0) / maxRuns
  }
  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector
  println("List: " + getWriteTime(numbersList)/1000 + "ms") //keeps reference to tail, but updating in middle takes a long time
  println("Vector: " + getWriteTime(numbersVector)/1000 + "ms") //depth of tree is small, but entire 32 element chunk is replaced
/*
List: 3514.8 ms
Vector: 7.8 ms
 */


}
