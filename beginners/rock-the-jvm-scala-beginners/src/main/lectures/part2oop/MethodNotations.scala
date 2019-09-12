package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String){
    def likes(movie: String): Boolean = movie == favoriteMovie
    def &(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hello, my name is $name and I like $favoriteMovie" //parens required
  }

  val mary = new Person("Mary", "Something About Mary")
  println(mary.likes("Something About Mary"))
  println(mary likes "Something About Mary") // <== natural language (syntactic sugar)
  // infix notation = operator notation - only works with methods that have a single parameter
  val tom = new Person("Tom", "Fight Club")
  println(mary & tom) // <-- the method becomes an operator - the method could even be a symbol
  println(1.+(2)) // even regular math operators are methods

  //Akka actors have methods such as ! and ?


  //prefix notation

  val x = -1 //the negative is really a unary operator, which is really a method
  val y = 1.unary_-
  //unary operators: + - ~ !
  println(x == y) //true
  println(!mary) // "Mary what the heck?!

  //postfix notation - only for methods without parameters **DEPRECATED Scala 2.13**
  println(mary.isAlive) //true
  //  println(mary isAlive) this doesn't trigger an error in the IDE, but the scala compiler doesn't like it
  /*
  Error:(34, 20) postfix operator isAlive needs to be enabled
by making the implicit value scala.language.postfixOps visible.
----
This can be achieved by adding the import clause 'import scala.language.postfixOps'
or by setting the compiler option -language:postfixOps.
See the Scaladoc for value scala.language.postfixOps for a discussion
why the feature needs to be explicitly enabled.
  println(mary isAlive)
   */

  //apply
  println(mary.apply())
  println(mary()) //this only works for the special apply() method

}
