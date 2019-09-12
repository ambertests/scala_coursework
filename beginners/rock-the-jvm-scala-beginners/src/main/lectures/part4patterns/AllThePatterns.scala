package lectures.part4patterns

import exercises.part2oop.inheritence.{EmptyList, LinkedList, MyList}

object AllThePatterns extends App {

  // 1. Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "THE Scala"
    case true => "the truth"
    case AllThePatterns => "A singleton object"
  }

  println(constants)

  //2. match anything

  //2.1 wildcard
  val matchAnything = x match {
    case _ => "it's a thing"
  }

  //2.2 variable
  val matchVariable = x match {
    case something => s"I've got $something"
  }

  // 3. tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) => "blep"
    case (something, 2) => s"I've got $something"
  }
  println(matchATuple)

  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case(_, (2,v)) => s"I've got $v"
  }
  println(matchNestedTuple)

  //4. Constructor pattern
  val aList: MyList[Int] = LinkedList(1, LinkedList(2, EmptyList))
  val matchAList = aList match {
    case EmptyList => "empty"
    case LinkedList(head, LinkedList(subhead, subtail)) => subhead
  }
  println(matchAList)

  //5. List patterns
  val standardList = List(1,3,5,8)
  val standardListMatching = standardList match {
    case List(1,_,_,_) => "extractor"
    case List(1,_*) => "list of arbitrary length"
    case 1 :: List(_) => "infix pattern"
    case List(1,3,5) :+8 => "another infix pattern"
  }
  println(standardListMatching)

  //6. type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => list
    case _ => "anything"
  }
  println(unknownMatch)

  // 7. name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ LinkedList(_,_) => nonEmptyList
    case LinkedList(1, rest @ LinkedList(2, _)) => rest
  }
  println(nameBindingMatch)

  // 8. multi-patterns
  val multiPattern = aList match {
    case EmptyList | LinkedList(0, _) => "compound pattern"
    case _ => "pattern didn't match"
  }
  println(multiPattern)

  // 9. if guards
  val secondElementSpecial = aList match {
    case LinkedList(_, LinkedList(specialElement, _)) if specialElement % 2 == 0 => "element was special"
    case _ => "element wasn't special"
  }
  println(secondElementSpecial)

  /*
  Question
   */

  val numbers = List(1,2,3)
  val numberMatch = numbers match {
    case listOfStrings: List[String] => "List of strings"
    case listOfNumbers: List[Int] => "List of numbers"
    case _ => "blah"
  }
  println(numberMatch) //List of strings
  /*
  JVM is backwards compatible back to Java1, which didn't have generic types
  So once the type-checking happens, the generics are ignored. So List[String]
  becomes just a plain List, which matches with List[Int]. Called "type erasure"
   */

}
