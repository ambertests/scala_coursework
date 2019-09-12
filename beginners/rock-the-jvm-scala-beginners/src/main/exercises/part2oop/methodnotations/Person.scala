package exercises.part2oop.methodnotations

class Person(val name: String, favoriteMovie: String, val age: Int = 0){
  def likes(movie: String): Boolean = movie == favoriteMovie
  def &(person: Person): String = s"${this.name} is hanging out with ${person.name}"
  def +(desc: String): Person = new Person(s"${this.name} ($desc)", this.favoriteMovie)
  def unary_! : String = s"$name what the heck?!"
  def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)
  def isAlive: Boolean = true
  def apply(): String = s"Hello, my name is $name and I like $favoriteMovie" //parens required
  def apply(count: Int): String = s"$name watched $favoriteMovie $count times"
  def learns(thing: String): String = s"$name is learning $thing"
}
