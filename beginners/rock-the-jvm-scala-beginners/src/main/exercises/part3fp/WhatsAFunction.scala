package exercises.part3fp

object WhatsAFunction extends App {

  def catFunction: ((String, String) => String) = (string1: String, string2: String) => string1 + string2
  println(catFunction("hello", "world"))



  val funception = new ((Int) => ((Int => Int))){
    override def apply(v1: Int): Int => Int = {
      (v2: Int) => v1 * v2
    }
  }

  val three = funception(3)
  println(three.getClass.getSimpleName)
  println(three(3)) //9
  println(funception(3)(4)) //curried function


}

