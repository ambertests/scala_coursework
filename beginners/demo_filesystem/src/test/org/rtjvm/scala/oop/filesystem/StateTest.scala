package org.rtjvm.scala.oop.filesystem

import org.scalatest.{FunSuite, Matchers}
import org.rtjvm.scala.oop.files.Directory



class StateTest extends FunSuite with Matchers {

  test("State object apply method: no output"){
    val rootDir: Directory = Directory.ROOT
    val state: State = State(rootDir, rootDir)
    state.root shouldEqual rootDir
    state.wd shouldEqual rootDir
    state.output shouldEqual ""
  }
  test("State object apply method: with output"){
    val rootDir: Directory = Directory.ROOT
    val output: String = "I'm the output"
    val state: State = State(rootDir, rootDir, output)
    state.root shouldEqual rootDir
    state.wd shouldEqual rootDir
    state.output shouldEqual output
  }
  test("setMessage creates a new State with the new message"){
    val rootDir: Directory = Directory.empty("foo", "bar")
    val state: State = State(rootDir, rootDir)
    val output: String = "I'm the output"
    val newState = state.setMessage(output)
    newState should not equal state
    newState.output shouldEqual output
  }

  test("show prints output followed by the shell token"){
    trait MockOutput extends Output{
      var lines: Seq[String] = Seq.empty
      override def print(s: String): Unit = lines = lines :+ s
    }
    val state = new State(Directory.ROOT, Directory.ROOT, "Ta-da!") with MockOutput
    state.show()
    state.lines should contain allOf (state.output, State.SHELL_TOKEN)
  }

}
