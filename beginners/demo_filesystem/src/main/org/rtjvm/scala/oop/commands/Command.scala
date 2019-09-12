package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.filesystem.State

trait Command extends (State => State)

object Command {
  val INCOMPLETE_COMMAND = "Incomplete command"
  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  val CD  = "cd"
  val TOUCH = "touch"
  val RM = "rm"
  val ECHO = "echo"
  val CAT = "cat"

  def emptyCommand: Command = (state: State) => state

  def incompleteCommand(name: String): Command =
    (state: State) => state.setMessage(INCOMPLETE_COMMAND)

  def from(input: String): Command = {
    if(input == null || input.isEmpty) emptyCommand
    else {
      val tokens: Array[String] = input.split(" ")
      if (MKDIR.equals(tokens.head)) {
        if (tokens.length < 2) incompleteCommand(MKDIR)
        else new MkdirCommand(tokens(1))
      }
      else if(LS.equals(tokens.head)) new LsCommand
      else if(PWD.equals(tokens.head)) new PwdCommand
      else if(CD.equals(tokens.head)) {
        if (tokens.length < 2) incompleteCommand(CD)
        else new CdCommand(tokens(1))
      }
      else if(TOUCH.equals(tokens.head)) {
        if (tokens.length < 2) incompleteCommand(CD)
        else new TouchCommand(tokens(1))
      }
      else if(RM.equals(tokens.head)) {
        if (tokens.length < 2) incompleteCommand(CD)
        else new RmCommand(tokens(1))
      }
      else if(ECHO.equals(tokens.head)) {
        new EchoCommand(tokens.tail.toList)
      }
      else if(CAT.equals(tokens.head)) {
        if (tokens.length < 2) incompleteCommand(CD)
        else new CatCommand(tokens(1))
      }
      else new UnknownCommand
    }
  }
}
