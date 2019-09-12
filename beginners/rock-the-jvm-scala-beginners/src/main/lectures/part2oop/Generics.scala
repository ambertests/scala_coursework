package lectures.part2oop

object Generics extends App {
 //[A] for generic type
  class MyList[+A]{
   //use the type A
   //if a covariant list this won't work
   //def add(element: A): MyList[A] = ???

   //this works because B is a super-type of A
   def add[B >:A](element: B): MyList[B] = ???
 }
  val intList = new MyList[Int]
  val strList = new MyList[String]

  class MyMap[Key, Value]{

  }
  val m = new MyMap[String, Int]

  object MyList{
    def empty[A]: MyList[A] = ???
  }

  class Animal
  class Cat extends Animal
  class Dog extends Animal
  // Cat and Dog are covariant - extending the same base class
  class CovariantList[+A]

  val animal:Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //replacing list of animals with list of cats
  //Can a Dog type be added to animalList?
  //yes, but list will change from Cat list to Animal list
  var animals: MyList[Cat] = new MyList[Cat]
  animals.add(new Dog)
  println(animals.isInstanceOf[MyList[Cat]])

  class InvariantList[A]
  //only Animals allowed
  val invariantList: InvariantList[Animal] = new InvariantList[Animal] //can't make it with Cat or Dog


  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  // what???

  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  //a trainer of a Cat is created from a generic Animal trainer

  //bounded types
  class Cage[A <: Animal] (animal: A)
  val cage = new Cage(new Dog)
  //cages can only be made with Animal types

  class Car
  //val cage2 = new Cage(new Car) //runtime error because a car is not an Animal


}
