package lectures.part3fp

object WhatsAFunction extends App {

  //DREAM: use functions as first class elements
  //problem: object-oriented programming sees everything as objects
  // so JVM would take functions wrapped up as class objects (ie Traits)

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) //because of how apply is treated, the doubler object
                      // can be called like a function

  //function types = Function[A,B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder = new Function2[Int, Int, Int] { // could be ((Int, Int) => Int)
    override def apply(a: Int, b: Int): Int = a+b
  }

  // all Scala functions are objects (Function1, Function2, etc)

  /*
  1. define a function which takes two strings and concatenates
  2. transform the MyPredicate and MyTransformer into function types
  3. define a function which takes an Int and returns another function
     which takes an Int and returns an Int
        - what is the type of this function
        - how to actually implement
   */
}

trait MyFunction[A,B]{
  def apply(element: A): B
}
