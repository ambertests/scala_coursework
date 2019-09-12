package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal{
    def eat: Unit
  }

  //compiler will create anonymous class on the spot
  val funnyAnimal: Animal = new Animal{
    override def eat: Unit = println("hahahahaha")
  }
  /*
  class AnonymousClasses$$anon$1 extends Animal{
    override def eat: Unit = println("hahahahaha")
  }
   */

  println(funnyAnimal.getClass) //class lectures.part2oop.AnonymousClasses$$anon$1

  funnyAnimal.eat

  class Person(name: String){
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help you?")
  }

  val jim = new Person("Jim"){ // has to have the superclass name parameter to create the anonymous class
    override def sayHi: Unit = println("Hi, my name is Jim, how can I be of service?")
  }
  println(jim.getClass) //lectures.part2oop.AnonymousClasses$$anon$2
  jim.sayHi
/*
1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
2. Generic trait MyTransformer[-A,B] with a method transformer(A) => B
3. MyList:
  - map(transformer) => MyList
  - filter(predicate) => MyList
  - flatMap(transformer from A to MyList[B]) => MyList[B]

  class EvenPredicate extends MyPredicate[Int] -> test returns if int is even or not
  class StringToIntTransformer extends MyTransformer[String, Int] -> transform String into Int

  [1,2,3].map(n*2) = [2,4,6]
  [1,2,3,4].filter(n%2) = [2,4]
  [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]


 */
}
