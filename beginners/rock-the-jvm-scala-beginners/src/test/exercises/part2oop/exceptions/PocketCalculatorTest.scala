package exercises.part2oop.exceptions

import org.scalatest.FunSpec

class PocketCalculatorTest extends FunSpec {
  describe("add") {
    describe("sum greater than Integer.MaxInt") {
      it("throws an OverflowException") {
        assertThrows[OverflowException] {
          PocketCalculator.add(Int.MaxValue, 1)
        }
        assertThrows[OverflowException] {
          PocketCalculator.add(1, Int.MaxValue)
        }
      }
    }
    describe("sum less than Integer.MinInt") {
      it("throws an UnderflowException") {
        assertThrows[UnderflowException] {
          PocketCalculator.add(Int.MinValue, -1)
        }
        assertThrows[UnderflowException] {
          PocketCalculator.add(-1, Int.MinValue)
        }
      }
    }
    describe("sum equal to Integer.MaxInt") {
      it("doesn't throw") {
        assert(PocketCalculator.add(Int.MaxValue, 0) == Int.MaxValue)
        assert(PocketCalculator.add(0, Int.MaxValue) == Int.MaxValue)
      }
    }
    describe("sum equal to Integer.MinInt") {
      it("doesn't throw") {
        assert(PocketCalculator.add(Int.MinValue, 0) == Int.MinValue)
        assert(PocketCalculator.add(0, Int.MinValue) == Int.MinValue)
      }
    }
    describe("regular addition") {
      it("doesn't throw") {
        assert(PocketCalculator.add(2, 3) == 5)
      }
    }
  }
  describe("subtract") {
    describe("difference less than Integer.MinInt") {
      it("throws an UnderflowException") {
        assertThrows[UnderflowException] {
          PocketCalculator.subtract(Int.MinValue, 1)
        }
      }
    }
    describe("difference larger than Integer.MaxInt") {
      it("throws an Overflow") {
        assertThrows[OverflowException] {
          PocketCalculator.subtract(1, Int.MinValue)
        }
      }
    }
    describe("difference equal to Integer.MinInt") {
      it("doesn't throw") {
        assert(PocketCalculator.subtract(Int.MinValue, 0) == Int.MinValue)
      }
    }
    describe("result greater than zero") {
      it("doesn't throw") {
        assert(PocketCalculator.subtract(4, 3) == 1)
      }
    }
    describe("result less than zero") {
      it("doesn't throw") {
        assert(PocketCalculator.subtract(3, 4) == -1)
      }
    }

  }
  describe("divide"){
    describe("division by zero"){
      it("throws MathCalculationException"){
        assertThrows[MathCalculationException]{
          PocketCalculator.divide(3, 0)
        }
      }
    }
    describe("regular division"){
      it("doesn't throw"){
        assert(PocketCalculator.divide(6, 3) == 2)
      }
    }
  }
  describe("multiply"){
    it("multiplies two numbers together"){
      assert(PocketCalculator.multiply(Int.MaxValue, Int.MaxValue) > 0)
      println(Int.MinValue * Int.MinValue)
    }
  }
}
