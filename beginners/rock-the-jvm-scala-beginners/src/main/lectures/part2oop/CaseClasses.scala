package lectures.part2oop

object CaseClasses extends App {

  /*
  creating classes involves a lot of boilerplate like equals, hashCode, toString, etc
   */

  case class Person(name: String, age: Int)

  //using the case keyword does the following

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name) // it is there automatically

  //2. sensible toString
  println(jim) // Person(Jim,34) instead of lectures.part2oop.CaseClasses$Person@4b6995df

  //3. equals and hashCode are implemented automatically
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) //true because it compares field values, not just hashCode

  //4. handy copy method
  val jim3 = jim.copy(age = 45) //name is Jim, age is 45
  println(jim3)

  //5. case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 30) //Person.apply
  println(mary)

  //6. case classes are serializable (Akka, http)

  //7. case classes have extractor patterns for Pattern Matching

  case object UnitedKingdom {
    def name: String = "The United Kingdom of Great Britain and Northern Island"
  }

  /*
  Ex
   */


}
