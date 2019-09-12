package org.rtjvm.scala.oop.files

import org.scalatest.{FunSuite, Matchers}

class DirEntryTest extends FunSuite with Matchers {

  class TestDirEntry(parentPath: String, name: String) extends DirEntry(parentPath, name){
    override def asDirectory: Directory = null

    override def asFile: File = null

    override def isDirectory: Boolean = false

    override def isFile: Boolean = false
  }

  test("path concatenates parent path and name"){
    val testDirEntry = new TestDirEntry("foo", "bar")
    testDirEntry.path shouldEqual "foo" + Directory.SEPARATOR + "bar"
  }

  test("path handles root path value correctly"){
    val testDirEntry = new TestDirEntry("", "blah")
    testDirEntry.path shouldEqual "/blah"
  }

}
