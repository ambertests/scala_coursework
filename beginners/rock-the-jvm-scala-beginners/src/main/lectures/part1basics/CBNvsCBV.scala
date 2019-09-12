package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  // => means call by name
  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime()) // time is computed, then passed to the function, which prints it twice
  calledByName(System.nanoTime()) // passes the *expression* System.nanoTime(), which is computed then printed each time

  def infinite(): Int = {
    1 + infinite()
  }

  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34) // stack overflow on the infinite() call
  printFirst(34, infinite()) // no overflow since the y parameter is never used, so infinite() is never called
}
