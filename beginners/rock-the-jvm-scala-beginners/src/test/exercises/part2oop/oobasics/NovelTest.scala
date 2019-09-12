package exercises.part2oop.oobasics

import exercises.part2oop.oobasics.{Novel, Writer}
import org.scalatest.{BeforeAndAfter, FunSuite}

class NovelTest extends FunSuite with BeforeAndAfter{

  var novel: Novel = _
  val author = new Writer("Jill", "Brown", 1968)
  val title = "The Best Book Ever"
  val year = 1998

  before{
    novel = new Novel(title, year, author)
  }

  test("novel title"){
    assert(novel.title == title)
  }

  test("novel author"){
    assert(novel.author == author)
  }

  test("novel release year"){
    assert(novel.releaseYear == year)
  }

  test("author's age at publication"){
    assert(novel.authorAgeAtPublication == year - author.yearOfBirth)
  }

  test("novel copy"){
    val newYear = 2019
    val reprint = novel.copy(newYear)
    assert(reprint.author == novel.author)
    assert(reprint.title == novel.title)
    assert(reprint.releaseYear == newYear)
  }

}
