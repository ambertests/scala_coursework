package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  // println(x.length) //null pointer exception
  //val ex: String = throw new NullPointerException //value is Nothing

  def getInt(withExceptions:Boolean): Int = {
    if(withExceptions) throw new RuntimeException("no ints available")
    else 42
  }

  val foo = try{
    getInt(true)
  }catch{
    case e:
      RuntimeException => 43
  }finally{
    //not part of the expression, only for side effects
    println("finally")
  }

  //the whole try/catch block is an expression
  println(foo)

  class MyException extends Exception
  val ex = new MyException
  //throw ex //lectures.part2oop.Exceptions$MyException

  /*
  1. crash program with OutOfMemoryError
  2. crash program with StackOverflowError
  3. PocketCalculator
   - add(x,y)
   - subtract(x,y)
   - multiply(x,y)
   - divide(x,y)

      Throw
        - OverflowException if add(x,y) exceeds Int.MAX_VALUE
        - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
   */

  //val array = Array.ofDim(Int.MaxValue) //java.lang.OutOfMemoryError: Requested array size exceeds VM limit

  def infinite: Int = 1 + infinite
  //val noLimit = infinite //java.lang.StackOverflowError

}
