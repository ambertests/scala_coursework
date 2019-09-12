package exercises.part2oop.oobasics

class Novel(val title: String, val releaseYear: Int, val author: Writer){
  def authorAgeAtPublication(): Int = releaseYear - author.yearOfBirth

  def isWrittenBy(a: Writer): Boolean = a.equals(this.author)

  def copy(newYear: Int): Novel = new Novel(this.title, newYear, this.author)
}
