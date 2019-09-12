package lectures.part2oop

object OOBasics extends App {

  val person = new Person("Amber", 49)
  println(person) // lectures.part2oop.Person@6e8dacdf
  println(person.age) //49
  println(person.x) //2
  person.greet("Joe") //Amber says: Hi, Joe
  person.greet() //Hi, I'm Amber

  val person2 = new Person("Baby")
  println(person2.age) //zero



}
// constructor is implicit in definition
// class parameters are not fields (same as Java) - adding "val" makes it a field
class Person (name: String, val age: Int){
  val x = 2 // this is a read-only field

  println("here's a person") //run on each instance of the class

  //methods
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name") //$name is the one passed to the method

  //overloading
  def greet(): Unit = println(s"Hi, I'm $name") //$name is the object name field

  //multiple constructors - only really useful for default parameters
  def this(name: String) = this(name, 0)
}
/*
Novel and Writer class

Writer: first name, surname, year of birth
  method - fullName

Novel: name, year of release, author (Writer)
  methods
    - authorAge
    - isWrittenBy(author)
    - copy (new year of release) = new instance of novel with new year of release
 */

/*
  Counter Class
   - receives and int value
   - method returns current count
   - method to increment and decrement => returns new Counter
   - overload inc and dec to receive parameter (amount)
 */
