package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.Directory
import org.rtjvm.scala.oop.filesystem.State
import org.scalatest.FunSpec

class CommandTest extends FunSpec {
  val orig = State(Directory.ROOT, Directory.ROOT)
  describe("emptyCommand function") {
    it("returns the original state") {
      val newState = Command.emptyCommand.apply(orig)
      assert(orig == newState)
    }
  }

  describe("incompleteCommand function"){
    it("returns new state with incomplete command message"){
      val newState = Command.incompleteCommand("blah").apply(orig)
      assert(newState != orig)
      assert(orig.root == newState.root)
      assert(orig.wd == newState.wd)
      assert(newState.output == Command.INCOMPLETE_COMMAND)
    }
  }
  describe("from function"){
    describe("with null input"){
      it("activates emptyCommand"){
        val newState = Command.from(null).apply(orig)
        assert(orig == newState)
      }
    }
    describe("with empty input") {
      it("activates emptyCommand") {
        val newState = Command.from("").apply(orig)
        assert(orig == newState)
      }
    }
    describe("with unknown input"){
      it("returns an UnknownCommand"){
        val cmd = Command.from("sdlfsl")
        assert(cmd.isInstanceOf[UnknownCommand])
      }
    }
    describe("mkdir with no other args"){
      it("activates incompleteCommand"){
        val newState = Command.from(Command.MKDIR).apply(orig)
        assert(newState.output == Command.INCOMPLETE_COMMAND)
      }
    }
    describe("mkdir and path arg"){
      it("returns a MkdirCommand"){
        val cmd = Command.from("mkdir blah")
        assert(cmd.isInstanceOf[MkdirCommand])
      }
    }
    describe("ls command arg"){
      it("returns an LSCommand"){
        val cmd = Command.from(Command.LS)
        assert(cmd.isInstanceOf[LsCommand])
      }
    }
    describe("pwd command arg"){
      it("returns a PwdCommand"){
        val cmd = Command.from(Command.PWD)
        assert(cmd.isInstanceOf[PwdCommand])
      }
    }
    describe("cd with no other args"){
      it("activates incompleteCommand"){
        val newState = Command.from(Command.CD).apply(orig)
        assert(newState.output == Command.INCOMPLETE_COMMAND)
      }
    }
    describe("cd and path arg"){
      it("returns a MkdirCommand"){
        val cmd = Command.from("cd blah")
        assert(cmd.isInstanceOf[CdCommand])
      }
    }
    describe("touch with no other args"){
      it("activates incompleteCommand"){
        val newState = Command.from(Command.TOUCH).apply(orig)
        assert(newState.output == Command.INCOMPLETE_COMMAND)
      }
    }
    describe("touch and path arg"){
      it("returns a MkdirCommand"){
        val cmd = Command.from("touch blah")
        assert(cmd.isInstanceOf[TouchCommand])
      }
    }

  }

}
