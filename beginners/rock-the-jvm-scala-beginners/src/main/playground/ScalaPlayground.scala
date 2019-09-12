package playground

object ScalaPlayground extends App {
  println("Hello, Scala!")
  class Foo(){
    var bar: Int = 0
  }
  val foo = new Foo
  println(foo.bar + " " + foo.hashCode())
  foo.bar = 1
  println(foo.bar + " " + foo.hashCode())
  val foo2 = new Foo
  println(foo2.bar + " " + foo2.hashCode())
}

object Cinderella

object PrinceCharming
