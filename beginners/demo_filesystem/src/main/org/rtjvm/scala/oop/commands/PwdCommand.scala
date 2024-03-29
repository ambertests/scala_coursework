package org.rtjvm.scala.oop.commands

import org.rtjvm.scala.oop.filesystem.State

class PwdCommand extends Command {

  override def apply(state: State): State = {
   state.setMessage(state.wd.path)
  }
}
