package exercises.part2oop.inheritence

import org.scalatest.FunSpec

class MyListTest extends FunSpec{
  describe("An EmptyList"){
    it("is an Object"){
      val e1:MyList[Int] = EmptyList
      val e2:MyList[Int] = EmptyList
      assert(e1 == e2)
    }
    it("is empty"){
      assert(EmptyList.isEmpty)
    }
    it("has a string value of []"){
      assert(EmptyList.toString == "[]")
    }
    it("throws on head"){
      assertThrows[NoSuchElementException](EmptyList.head)
    }
    it("throws on tail"){
      assertThrows[NoSuchElementException](EmptyList.tail)
    }
    it("creates a LinkedList on add"){
      val list = EmptyList.add(3)
      assert(list.isInstanceOf[LinkedList[Int]])
      assert(list.head == 3)
      assert(list.tail == EmptyList)
    }
  }
  describe("A LinkedList"){
    it("is not empty"){
      val list = EmptyList.add(3)
      assert(!list.isEmpty)
    }
    describe("constructor"){
      it("takes a head value"){
        val list = new LinkedList(3)
        assert(list.head == 3)
      }
      it("has an Empty tail by default"){
        val list = new LinkedList(3)
        assert(list.tail == EmptyList)
      }
      it("can take a head and tail value"){
        val t = new LinkedList(2)
        val list = new LinkedList(3, t)
        assert(list.tail == t)
      }
    }
    describe("add method"){
      it("returns a new LinkedList with added value at head and original list at tail"){
        val list = new LinkedList(3)
        val added = list.add(2)
        assert(added.isInstanceOf[LinkedList[Int]])
        assert(added.hashCode() != list.hashCode())
        assert(added.head == 2)
        assert(added.tail == list)
      }
    }
    describe("toString method"){
      it("returns complete list of all ints"){
        val list = EmptyList.add(1).add(2).add(3).add(4)
        assert(list.toString == "[1 2 3 4]")
      }
    }
    describe("contains: element exists"){
      it("returns true"){
        val list = new LinkedList("a").add("b").add("c")
        assert(list.contains("b"))
        assert(list.contains("a"))
        assert(list.contains("c"))
      }
    }
    describe("contains: element does not exist"){
      it("returns true"){
        val list = new LinkedList("a").add("b").add("c")
        assert(!list.contains("d"))
      }
    }
    describe("map with transformer"){
      it("returns a transformed list"){
        val list = EmptyList.add(1).add(2).add(3).add(4)
        val transformed = list.map((element: Int) => element * 2)
        assert(transformed.toString == "[2 4 6 8]")
      }
  }
    describe("map with special transformer"){
      it("returns a transformed list"){
        val list = EmptyList.add("1").add("2").add("3")
        val transformed = list.map((v1: String) => v1.toInt)
        assert(transformed.toString == "[1 2 3]")
        assert(transformed.head.isInstanceOf[Int])

      }
    }
    describe("filter with special predicate"){
      it("returns a filtered list"){
        val list = EmptyList.add(1).add(2).add(3).add(4)
        val filtered = list.filter((i: Int) => i % 2 == 0)
        assert(filtered.toString == "[2 4]")

      }
    }
    describe("flatMap with transformer") {
      it("returns a flatMapped list") {
        val list = EmptyList.add(1).add(2).add(3).add(4)
        val transformed = list.flatMap((element: Int) => new LinkedList[Int](element + 1, new LinkedList[Int](element)))
        assert(transformed.toString == "[1 2 2 3 3 4 4 5]")
      }
    }
  }
  describe("A LinkedList that is a case class"){
    describe("automatic equals method"){
      it("compares values of two lists"){
        val list1 = EmptyList.add(4).add(76).add(23)
        val list2 = EmptyList.add(4).add(76).add(23)
        assert(list1 == list2)
      }
    }
    describe("automatic apply method for companion object"){
      it("creates a new list"){
        val list = LinkedList(3)
        assert(list.contains(3))
      }
    }
  }
  describe("LinkedList supports for comprehensions"){
    it("returns all combos"){
      val list1 = EmptyList.add(1).add(2)
      val list2 = EmptyList.add("a").add("b")
      val combos = for{
        n <- list1
        c <- list2
      } yield c + n
      assert(combos.contains("a1"))
      assert(combos.contains("b1"))
      assert(combos.contains("a2"))
      assert(combos.contains("b2"))
    }
  }

}
