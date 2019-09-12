package lectures.part2oop

//multiple objects in curly braces
import playground.{Cinderella, PrinceCharming}
//can alias to allow classes with the same name from different packages
import java.sql.{Date => SqlDate}
import java.util.Date


object PackagingAndImports extends App {
   //package object (specific to Scala)
   //used for global methods and objects
  sayHello
  println(SPEED_OF_LIGHT)
  val c = Cinderella
  val p = PrinceCharming
  val sqlDate = new SqlDate(0)
  val date = new Date(0)

  //default imports
  //java.lang
  //scala
  //scala.Predef
}
