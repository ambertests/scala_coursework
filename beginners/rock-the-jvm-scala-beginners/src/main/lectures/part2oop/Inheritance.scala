package lectures.part2oop

import com.sun.tools.internal.xjc.generator.bean.DualObjectFactoryGenerator

object Inheritance extends App {

  class Animal{
    //public by default - could also be protected or private
    def eat = println("nomnom")
    val creatureType = "wild"
  }

  class Cat extends Animal
  val cat = new Cat
  cat.eat //nomnom

  //constructors
  class Person(name: String, age: Int)

  //need to specify base class parameters in extends clause
  class Adult (name: String, age: Int, idCard: String) extends Person(name, age)

  //overriding

  class Dog(override val creatureType: String) extends Animal{
    override def eat: Unit = {
      println("crunch, crunch")
      super.eat // <-- calls base method
    }
    //can also override vals and fields
  }
  val dog = new Dog("K9")
  dog.eat //crunch crunch
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat //crunch crunch <-- uses the override method even if it is "Animal"

  // don't confuse overriding with overloading!

  // use final keyword to prevent derived classes from overriding methods, also works for classes
  // seal the class to prevent extension in *other* files - same file is still ok


}
