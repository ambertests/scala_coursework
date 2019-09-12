package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.Directory
import org.rtjvm.scala.oop.filesystem.State
import org.scalatest.{FunSuite, Matchers}

class TouchCommandTest extends FunSuite with Matchers {

  test("touch command creates a new file"){
    val root = Directory.ROOT
    val state = State(root, root)
    val cmd = new TouchCommand("blah")
    val touched = cmd(state)
    touched.wd.contents should not be empty
    val file = touched.wd.contents.head
    file.name shouldEqual "blah"
    file.isFile shouldBe true
  }

}
