package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.files.Directory
import org.rtjvm.scala.oop.filesystem.State
import org.scalatest.{FunSuite, Matchers}

class PwdCommandTest extends FunSuite with Matchers {
  test("pwd returns the path of the working directory"){
    val state = State(Directory.ROOT, Directory.empty("/blah/bloop", "blorp"))
    val cmd = new PwdCommand
    cmd.apply(state).output shouldEqual state.wd.path
  }

}
