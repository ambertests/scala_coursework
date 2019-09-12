package exercises.part3fp

object HOFAndCurries extends App {
  /*
  1. Expand myList
    - foreach method A => Unit
    [1,2,3].foreach(x => println(x)) prints each item on a new line

    - sort function ((A,A) => Int) => MyList
    [1,2,3].sort((x,y) => y - x) => [3,2,1]

    - zipWith function (list, (A, A) => B) => MyList[B]
    [1,2,3].zipWith([4,5,6], x * y) => [1*4, 2*5, 6*3] => [4,10,18]

    - fold(start)(function) => a value
    [1,2,3].fold(0, (x+y)) = 6

   2. toCurry(f: (Int, Int) => Int): Int => Int => Int
      fromCurry(f: (Int => Int => Int)): (Int, Int) => Int

   3. compose(f, g) => x => f(g(x))
      andThen(f, g) => x => g(f(x))


   */

  def toCurry(f: (Int, Int) => Int): Int => Int => Int = {
    x => y => f(x,y)
  }
  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
    (x,y) => f(x)(y)
  }
  def compose[A, B, T](f: A => B, g: T => A): T => B = {
    x => f(g(x))
  }
  def andThen[A, B, C](f: A => B, g: B => C): A => C = {
    x => g(f(x))
  }

}
