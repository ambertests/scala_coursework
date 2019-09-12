package lectures.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head, list.tail)

  //map
  println(list.map(_+ 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(_%2 == 0))

  //flatMap
  val toPair = (x:Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")
  val combos = numbers.flatMap(n => chars.flatMap(c => colors.map(color => c + n.toString + "-" + color)))
  println(combos)

  //foreach
  list.foreach(println)

  //for-comprehensions: more readable than the flatMap.map chain above
  val forCombinations = for{
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color

  println(forCombinations)

  for{
    n <- numbers
  } println(n)

  list.map{
    x=> x*2
  }

  /*
  1. MyList supports for comprehensions?
  2. small collection of at most one element - Maybe[+T]
   */


}
