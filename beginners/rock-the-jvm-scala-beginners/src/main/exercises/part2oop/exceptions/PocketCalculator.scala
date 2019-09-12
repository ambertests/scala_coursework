package exercises.part2oop.exceptions

class OverflowException extends Exception{
  override def getMessage: String = "sum is greater than " + Int.MaxValue
}
class UnderflowException extends  Exception{
  override def getMessage: String = "difference is less than " + Int.MinValue
}
class MathCalculationException extends Exception{
  override def getMessage: String = "cannot divide by zero"
}

object PocketCalculator{
  def add(x: Int, y:Int): Int ={
    val result = x + y
    if(x > 0 && y > 0 && result < 0) throw new OverflowException
    else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
    else result
  }
  def subtract(x: Int, y: Int): Int = {
    val result = x - y
    if(x > 0 && y < 0 && result < 0) throw new OverflowException
    else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
    else result
  }
  def multiply(x: Int, y: Int): Int ={
    val result = x * y
    if(x > 0 && y > 0 && result < 0) throw new OverflowException
    else if(x < 0 && y < 0 && result < 0) throw new OverflowException
    else if(x < 0 && y > 0 && result > 0) throw new UnderflowException
    else if(x > 0 && y < 0 && result > 0) throw new UnderflowException
    else result
  }
  def divide(x: Int, y: Int): Int = {
    if(y == 0) throw new MathCalculationException
    else x/y
  }
}
