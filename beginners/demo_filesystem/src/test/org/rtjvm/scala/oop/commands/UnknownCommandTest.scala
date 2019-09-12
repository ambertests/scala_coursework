package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.Directory
import org.rtjvm.scala.oop.filesystem.State
import org.scalatest.FunSpec

class UnknownCommandTest extends FunSpec {
  describe("apply method"){
    val cmd = new UnknownCommand
    val orig = State(Directory.ROOT, Directory.ROOT)
    it("creates new state with output of UnknownCommand.MESSAGE"){
      val newState = cmd.apply(orig)
      assert(newState != orig)
      assert(orig.root == newState.root)
      assert(orig.wd == newState.wd)
      assert(newState.output == UnknownCommand.MESSAGE)
    }
  }

}
