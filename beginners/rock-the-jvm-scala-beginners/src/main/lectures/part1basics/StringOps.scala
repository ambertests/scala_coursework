package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  //same string functions as Java
  println(str.charAt(2)) // l
  println(str.substring(7, 11)) // I am
  println(str.split(" ").toList) // list of words
  println(str.startsWith("Hello")) //true
  println(str.replace(" ", "-"))
  println(str.toLowerCase)
  println(str.length) // no parameters, so doesn't need parens

  //Scala-specific utils

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2)) //first two chars

  // S-interpolators
  val name = "Jane"
  val age = 5
  val greeting = s"Hello, my name is $name and I am $age years old" //the s makes it an interpreted string
  println(greeting)
  val greeting2 = s"Hello, my name is ${name.reverse} and I am ${age + 1} years old" //can also have expressions in {}
  println(greeting2)

  // F-interpolators
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute" //printf style formatting 1.20 (2-digit precision)
  println(myth)

  // raw interpolator
  println(raw"This is a \n newline") //prints the literal \n instead of escaping as a newline
  val escaped = "This is a \n newline"
  println(raw"$escaped") //raw doesn't apply to the injected string, so this prints the actual new line character



}
