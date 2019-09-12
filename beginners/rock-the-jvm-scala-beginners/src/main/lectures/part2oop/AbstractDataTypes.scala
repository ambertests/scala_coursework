package lectures.part2oop

object AbstractDataTypes extends App {

  // fields and methods defined by not implemented
  abstract class Animal {
    val creatureType: String
    def eat(): Unit
  }

  class Dog extends Animal {
    //override keyword is optional
    val creatureType: String = "Canine"
    def eat(): Unit = println("crunch crunch")
  }

  // traits
  trait Carnivore{
    //abstract fields and methods
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fish"
  }

  class Crocodile extends Animal with Carnivore{
    val creatureType: String = "croc"

    def eat(): Unit = println("nom nom nom")

    def eat(animal: Animal): Unit = println(s"I'm a $creatureType and I'm eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  //how are traits different from abstract classes?
  // 1. traits do not have constructor parameters
  // 2. can only extend one class, but can use multiple traits
  // 3. choose a trait to describe a type of behavior


  //type hierarchy
  // scala.Any <-- scala.AnyRef (String, List, etc) <-- scala.Null <-- scala.Nothing
}
