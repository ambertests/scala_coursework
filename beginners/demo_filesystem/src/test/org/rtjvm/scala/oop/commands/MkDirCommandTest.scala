package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.Directory
import org.rtjvm.scala.oop.filesystem.State
import org.scalatest.{FunSuite, Matchers}

class MkDirCommandTest extends FunSuite with Matchers {

  test("touch command creates a new file"){
    val root = Directory.ROOT
    val state = State(root, root)
    val cmd = new MkdirCommand("blah")
    val made = cmd(state)
    made.wd.contents should not be empty
    val dir = made.wd.contents.head
    dir.name shouldEqual "blah"
    dir.isDirectory shouldBe true
  }

}
