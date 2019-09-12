package exercises.part2oop.oobasics

import exercises.part2oop.oobasics.Counter
import org.scalatest.FunSpec

class CounterTest extends FunSpec {

  describe("A Counter"){
    describe("created with defaults"){
      it("should have a value of zero"){
        val c = new Counter()
        assert(c.current == 0)
      }
    }
    describe("created with parameter"){
      it("should have that value"){
        val c = new Counter(5)
        assert(c.current == 5)
      }
    }
    describe("using default increment"){
      it("should create a new counter with a value increased by one"){
        val c = new Counter(5)
        val c2 = c.increment
        assert(c.current == 5)
        assert(c2.current == 6)
      }
    }
    describe("using default decrement"){
      it("should create a new counter with a value decreased by one"){
        val c = new Counter(5)
        val c2 = c.decrement
        assert(c.current == 5)
        assert(c2.current == 4)
      }
    }
    describe("using custom increment"){
      it("should create a new counter with a value increased by that amount"){
        val c = new Counter(5)
        val c2 = c.increment(30)
        assert(c.current == 5)
        assert(c2.current == 35)
      }
    }
    describe("using custom decrement"){
      it("should create a new counter with a value decreased by that amount"){
        val c = new Counter(5)
        val c2 = c.decrement(2)
        assert(c.current == 5)
        assert(c2.current == 3)
      }
    }
  }
  describe("/v1/location"){
    describe("foo.bar.baz"){
      it("does the thing"){
        assert(true)
      }
    }
  }

}
