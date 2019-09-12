package lectures.part3fp

object TuplesAndMaps extends App {

    //tuples = finite ordered "lists"
  val aTuple = (2, "hello scala") // Tuple2[Int, String] = (Int, String)
  //max 22 different types

  println(aTuple._1 + "|" + aTuple._2)
  println(aTuple.copy(_2 = "goodbye java"))
  println(aTuple.swap)

  //maps = key -> value
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 5551234), "Bob" -> 5554321).withDefaultValue(-1)
  println(phoneBook)
  println(phoneBook.contains("Jim")) //true
  println(phoneBook("Bob"))
  println(phoneBook("Mary"))

  println(phoneBook.map[String, Int](pair => (pair._1.toLowerCase -> pair._2)))
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)
  println(phoneBook.view.mapValues(number => number * 10).toMap)

  //conversions to other collections
  println(phoneBook.toList) //list of tuples
  println(List(("Daniel", 555)).toMap) //map
  val names = List("Bob", "James", "Barbara", "Angela", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) //mapping of letter to list of applicable names
  //HashMap(J -> List(James, Jim), A -> List(Angela), B -> List(Bob, Barbara), D -> List(Daniel))
}
