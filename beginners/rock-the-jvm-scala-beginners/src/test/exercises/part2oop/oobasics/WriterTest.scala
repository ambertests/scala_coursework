package exercises.part2oop.oobasics

import exercises.part2oop.oobasics.Writer
import org.scalatest.{BeforeAndAfter, FunSuite}

class WriterTest extends FunSuite with BeforeAndAfter{
  var writer: Writer = _
  val fn = "Amber"
  val sn = "Race"
  val yob = 1970

  before{
    writer = new Writer(fn, sn, yob)
  }

  test("writer first name"){
    assert(writer.firstName == fn)
  }

  test("writer surname"){
    assert(writer.surname == sn)
  }

  test("writer year of birth"){
    assert(writer.yearOfBirth == yob)
  }

  test("writer's full name") {
    assert(writer.fullName == s"$fn $sn")
  }

  test("equality"){
    val w2 = new Writer(fn, sn, yob)
    assert(writer == w2)
  }

  test("not equal: not a Writer"){
    assert(writer != "writer")
  }

  test("not equal: first name"){
    val w2 = new Writer("Joe", sn, yob)
    assert(writer != w2)
  }

  test("not equal: last name"){
    val w2 = new Writer(fn, "Blow", yob)
    assert(writer != w2)
  }

  test("not equal: yob"){
    val w2 = new Writer(fn, sn, 1980)
    assert(writer != w2)
  }


}
