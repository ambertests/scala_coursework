package lectures.part2oop

object Objects extends App {

  // Scala does not have class-level static functionality like Java does
  // This is the equivalent, using an object instead of a class
  object Person { //objects don't have parameters like class constructors do
    val EYE_COUNT = 2

    //objects can have methods, too
    def canFly: Boolean = false

    //factory method for Person class using apply
    def apply(mother: Person, father: Person): Person = new Person("Junior")
  }

  val mary = Person
  val john = Person
  println(mary == john) // true: these are actually the same singleton object

  class Person(val name: String)

  val jack = new Person("Jack")
  val jill = new Person("Jill")
  println(jack == jill) // false because these are instances of the Person *class*

  val jr = Person(jill, jack) //instantiating via the Person object apply method
  println(jr.name) //Junior

  // Scala Applications == Scala object with a main method:
  // def main(args: Array[String]): Unit
  // this becomes a public static void main method in the JVM





}
