package org.rtjvm.scala.oop.files

import org.scalatest.{FunSuite, Matchers}

class DirectoryTest extends FunSuite with Matchers {

  test("Directory.ROOT returns an empty root directory"){
    val root = Directory.ROOT
    root.path shouldEqual Directory.SEPARATOR
    root.contents shouldBe empty
  }
  test("Directory.empty returns an empty directory with the given path"){
    val dir = Directory.empty("/this/is/the/path/to", "empty")
    dir.path shouldEqual "/this/is/the/path/to/empty"
    dir.contents shouldBe empty
  }
  test("hasEntry returns true when directory contains the entry"){
    val dir = new Directory(Directory.ROOT_PATH, "var", List(Directory.empty("/var", "log")))
    dir.hasEntry("log") shouldBe true
  }
  test("hasEntry returns false when directory doesn't the entry"){
    val dir = new Directory(Directory.ROOT_PATH, "var", List(Directory.empty("/var", "log")))
    dir.hasEntry("blah") shouldBe false
  }
  test("hasEntry returns false when directory is empty"){
    val dir = Directory.empty("/var", "log")
    dir.hasEntry("blah") shouldBe false
  }
  test("getAllFoldersInPath returns empty for default root path"){
    val root = Directory.ROOT
    root.getAllFoldersInPath shouldBe empty
  }
  test("getAllFoldersInPath returns list of directory names from path"){
    val dir = Directory.empty("/this/is/the", "directory")
    dir.getAllFoldersInPath should contain allOf("this", "is", "the", "directory")
  }
  test("addEntry adds a new entry to Directory contents"){
    val dir = Directory.ROOT
    val added = dir.addEntry(Directory.empty(dir.path, "var"))
    added.path shouldEqual Directory.SEPARATOR
    added.hasEntry("var") shouldBe true
  }
  test("findEntry finds the named DirEntry object in the Directory contents"){
    val dir = new Directory(Directory.ROOT_PATH, "var", List(Directory.empty("/var", "log")))
    val logDir = dir.findEntry("log")
    logDir should not be None
    logDir.get.name shouldEqual "log"
  }
  test("findEntry should return None when entry doesn't exist"){
    val root = Directory.ROOT
    val varDir = root.findEntry("var")
    varDir shouldBe None
  }
  test("replaceEntry replaces named entry with new one"){
    val dir = new Directory(Directory.ROOT_PATH, "var", List(Directory.empty("/var", "log")))
    val replaced = dir.replaceEntry("log", Directory.empty("/var", "www"))
    replaced.hasEntry("www") shouldBe true
    replaced.hasEntry("log") shouldBe false
  }
  test("replaceEntry throws exception if named entry doesn't exist"){
    val dir = Directory.ROOT
    a [NoSuchElementException] should be
    thrownBy(dir.replaceEntry("var", Directory.empty(Directory.ROOT_PATH, "www")))
  }
  test("isDirectory returns true"){
    Directory.ROOT.isDirectory shouldBe true
  }
  test("isFile returns false"){
    Directory.ROOT.isFile shouldBe false
  }
  test("root directory returns true for isRoot"){
    Directory.ROOT.isRoot shouldBe true
  }
  test("named directory with empty path returns true for isRoot"){
    val dir = Directory.empty("", "blah")
    dir.isRoot shouldBe true
  }
  test("unnamed directory with non-empty path returns false for isRoot"){
    val dir = Directory.empty("foo", "")
    dir.isRoot shouldBe false
  }
  test("asDirectory returns same object"){
    val de: DirEntry = Directory.empty("blah", "bloop")
    de.asDirectory shouldEqual de
  }
  //test("findDescendent")
}
