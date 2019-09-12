package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int = 42
  println(x)
  // VALS ARE IMMUTABLE
  // x = 2 will cause compiler error

  val y = 42
  // Types of vals are optional - compiler can infer types
  // val y: Int = "abc" will cause an error
  println(y)

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = true
  val aChar: Char = 'x'

  val anInt: Int = x //4 bytes
  val aShort: Short = 2342 //2 bytes
  val aLong: Long = 9835708983745L //8 bytes
  val aFloat: Float = 3.14f
  val aDouble: Double = 3.14 // no 'f' needed

  //variables
  var aVariable: Int = 4
  aVariable = 5 //variables can be reassigned, unlike vals


}
