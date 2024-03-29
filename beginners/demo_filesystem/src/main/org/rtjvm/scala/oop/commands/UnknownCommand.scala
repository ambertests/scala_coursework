package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.filesystem.State

class UnknownCommand extends Command {
  override def apply(state: State): State = {
    state.setMessage(UnknownCommand.MESSAGE)
  }

}

object UnknownCommand {
  val MESSAGE = "Command not found"
}
