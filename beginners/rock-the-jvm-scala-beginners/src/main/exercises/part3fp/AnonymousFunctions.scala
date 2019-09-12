package exercises.part3fp

object AnonymousFunctions extends App {


  /*Exercises
  1) replace all functionx calls in MyList with lambdas
  2) Rewrite the curried adder as an anonymous function
   */
  val funception = new ((Int) => ((Int => Int))){
    override def apply(v1: Int): Int => Int = {
      (v2: Int) => v1 * v2
    }
  }

  val superAdder = (x:Int) => (y:Int) => x + y
  println(superAdder(3)(4)) //7

}
