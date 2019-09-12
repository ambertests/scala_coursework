package exercises.part2oop.methodnotations

import org.scalatest.FunSpec

class PersonTest extends FunSpec {

  describe("A Person"){
    val joe = new Person("Joe", "Joe Vs The Volcano")
    describe("using the plus operator with a description") {
      it("creates a new person with the name and description in parenthesis") {
        var js = joe + "the schmoe"
        assert(js.name == "Joe (the schmoe)")
      }
    }
    describe("using the unary + operator"){
      it("creates a new person with the age incremented by one"){
        var j19 = +joe
        assert(j19.age == joe.age + 1)
      }
    }
    describe("learns a thing"){
      it("returns a string saying what the person is learning"){
        assert((joe learns "Scala") == "Joe is learning Scala")
      }
    }
    describe("using the apply with integer method"){
      it("returns a string saying how many times the person watched their favorite movie"){
        assert(joe(2) == "Joe watched Joe Vs The Volcano 2 times")
      }
    }


  }

}
