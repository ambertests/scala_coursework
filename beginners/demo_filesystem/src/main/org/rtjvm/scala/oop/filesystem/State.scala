package org.rtjvm.scala.oop.filesystem

import org.rtjvm.scala.oop.files.Directory

class State(val root: Directory, val wd: Directory, val output: String) extends Output {

  def show(): Unit = {
    print(output)
    print(State.SHELL_TOKEN)
  }

  def setMessage(message: String): State = {
    State(root, wd, message)
  }

}

object State {
  val SHELL_TOKEN = "$ "

  def apply(root: Directory, wd: Directory, output: String = ""): State = {
    new State(root, wd, output)
  }
}


trait Output {

  def print(s: String): Unit = Console.print(s)

}
